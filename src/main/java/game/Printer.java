package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Creature;
import entity.Character;

public class Printer {

    private final Dungeon dungeon; // Game Dungeon
    private final Tracker tracker; // Game Tracker
    private final String outputType; // Output options:
    // OneScreen, ShowAll, ShowEnding, ShowNone

    private final Scanner scanner
        = new java.util.Scanner(System.in); // A Scanner for awaiting user input

    private static final int MAXHEALTH = 3;


    /**
     * @param dungeon Dungeon
     * @param output String
     * @param tracker Tracker
     *
     * Construct the printer.
     */
    public Printer(final Dungeon dungeon, final Tracker tracker,
        final String output) {
            this.dungeon = dungeon; // Game Dungeon
            this.tracker = tracker; // Game Tracker
            this.outputType = output; // Output type String
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
        if (outputType != "ShowNone") {
            // Print Game Status
            System.out.println();
            printGameStatus();

            // Level 0
            Room startingRoom = dungeon.getRoom("(0-1-1)");
            ArrayList<String> occupancyArray
                = getOccupancyStringArray(startingRoom);
            String occupancyString
                = occupancyArray.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
            System.out.println(occupancyString);

            // Levels 1, 2, 3, 4
            PrinterColumns columns = new PrinterColumns();
            for (int l = 1; l <= 4; ++l) {
                addLevelArray(l, columns);
            }
            columns.print();

            // Print Character/Creature Status
            printCharacterStats();
            printCreatureStats();
        }
    }


    /**
     * A pause method between turns asking the player to continue.
     */
    public void printPause() {
        if (outputType == "OneScreen") {
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
            clearScreen();
        }
    }


    /**
     * @param room Room
     * @return ArrayList<String>
     *
     * This method gets the string for displaying occupancy in each Room.
     */
    private ArrayList<String> getOccupancyStringArray(final Room room) {
        // Characters in Room
        ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
        String characterString = new String();
        for (Character c:charactersInRoom) {
            characterString += c.getName();
            characterString += " ";
        }

        // Creatures in Room
        ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
        String creatureString = new String();
        for (Creature c:creaturesInRoom) {
            creatureString += c.getName();
            creatureString += " ";
        }

        // Full Room Occupancy String Array
        ArrayList<String> occupancyArray = new ArrayList<String>();
        occupancyArray.add(room.getName());
        occupancyArray.add(" : ");
        occupancyArray.add(characterString);
        occupancyArray.add(" : ");
        occupancyArray.add(creatureString);
        return occupancyArray;
    }


    /**
     * @param level int
     * @param row int
     * @param columns Columns
     *
     * This method adds a row of the Dungeon and
     * its occupancy to the Columns object.
     */
    private void addRowArray(final Integer level, final Integer row,
        final PrinterColumns columns) {
            ArrayList<Room> rowRooms = new ArrayList<Room>();
            rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
            rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
            rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));

            ArrayList<String> rowArray = new ArrayList<String>();
            for (Room r: rowRooms) {
                for (String s: getOccupancyStringArray(r)) {
                    rowArray.add(s);
                }
            }
            columns.addLine(rowArray);
    }



    /**
     * @param level int
     * @param columns Columns
     *
     * This method adds a level of the Dungeon and its occupancy
     * to the Columns object.
     */
    private void addLevelArray(final Integer level,
            final PrinterColumns columns) {
            for (int r = 0; r <= 2; ++r) {
                addRowArray(level, r, columns);
            }
    }

    /**
     * Shows an overview of game information such as win conditions and entitys.
     */
    private void printGameStatus() {
        System.out.print("Game Status: ");
        System.out.print(" Round: ");
        System.out.print(tracker.getRoundCount());
        System.out.print(" Characters: ");
        System.out.print(tracker.getCharacterList().size());
        System.out.print(" Creatures: ");
        System.out.print(tracker.getCreatureList().size());
        System.out.print(" Treasures Collected: ");
        System.out.println(tracker.getTreasureCount());
    }

    /**
     * This method prints Character stats: name, treausres, hp.
     */
    private void printCharacterStats() {
        String tableHeader = new String("Adventurers\tDamage\tTreasure");
        System.out.println(tableHeader);
        for (Character c: tracker.getCharacterList()) {
            String name = c.getName();
            String treasureString = c.getInventoryString();
            Integer damage = MAXHEALTH - c.getHealth();

            String characterStats
                = new String(name + "\t\t" + damage + "\t" + treasureString);
            System.out.println(characterStats);
        }
    }


    /**
     * This method prints Creature stats: name and number remaining.
     */
    private void printCreatureStats() {
        ArrayList<String> creatureSet = new ArrayList<String>();
        creatureSet.add("Orbiter");
        creatureSet.add("Seeker");
        creatureSet.add("Blinker");
        String tempString;
        int[] counts = {0, 0, 0};
        for (Creature c: tracker.getCreatureList()) {
            counts[creatureSet.indexOf(c.getName())] += 1;
        }
        System.out.println();
        for (String creature: creatureSet) {
            tempString = new String(creature + "(s) - "
                + counts[creatureSet.indexOf(creature)] + " Remaining");
            System.out.println(tempString);
        }
    }


    /**
     * Prints the fight results.
     *
     * Example of Observer pattern
     * Printer has subscribed to values from the Tracker
     * that are updated whenever a fighting event is published.
     */
    public void printFightResults() {
        if (outputType != "ShowNone") {
            HashMap<String, String> fightValues = tracker.getFightValues();
            String result = fightValues.get("result");
            // options: "CharacterWon", "CreatureWon", "FightSkipped"
            if (result == "FightSkipped") {
                // If Fight skipped
                System.out.println("Fight Skipped");
            } else {
                // Fight not skipped
                String character = fightValues.get("character");
                String characterRoll = fightValues.get("characterRoll");
                String creature = fightValues.get("creature");
                String creatureRoll = fightValues.get("creatureRoll");

                System.out.print("Fight: ");
                System.out.print(character + ": ");
                System.out.print(characterRoll);
                System.out.print(" " + creature + ": ");
                System.out.print(creatureRoll);

                if (result == "CharacterWon") {
                    // If Characer Won
                    System.out.println(" " + character + " Wins :D ");

                    System.out.print(character + " celebrates!: ");
                    tracker.getCelebration().celebrate();
                    System.out.println("");
                } else if (result == "CreatureWon") {
                    // If Creature Won
                    System.out.println(" Creature Wins :( ");
                }
            }
        }
    }


    /**
     * Prints the results of treasure hunting.
     *
     * Example of Observer pattern,
     * Printer has subscribed to values from the Tracker
     * that are updated whenever a treasure hunting event is published.
     */
    public void printTreasureHuntResults() {
        if (outputType != "ShowNone") {
            HashMap<String, String> treasureValues
                = tracker.getTreasureHuntValues();
            String character = treasureValues.get("character");
            String result = treasureValues.get("result");
            // "TreasureFound", "TreasureNotFound", "DuplicateTreasureFound"
            String score = treasureValues.get("score");

            System.out.print(character);
            System.out.print(" Treasure Hunt: ");
            System.out.print(score);
            if (result == "TreasureFound") {
                // If Treasure Found
                String treasure = treasureValues.get("treasure");
                System.out.println(" Success! ");
                System.out.println("Treasure: " + treasure);
            } else if (result == "DuplicateTreasureFound") {
                // If Treasure Already Found
                String treasure = treasureValues.get("treasure");
                System.out.println(" Success! ");
                System.out.println("Treasure: " + treasure);
                System.out.println(treasure + " Already in Inventory :(");
            } else if (result == "TreasureNotFound") {
                // If Treasure Not Found
                System.out.println(" Fail :(");
            }
        }
    }


    /**
     * Prints the starting screen.
     */
    public void printStartingScreen() {
        if (outputType != "ShowNone") {
            System.out.println("Starting Game!");
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
        }
    }


    /**
     * Clears the screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
