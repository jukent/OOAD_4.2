package game;

import java.util.ArrayList;
import java.util.Scanner;

import celebration.*;
import dungeon.*;
import entity.*;
import entity.Character;
import fight.FightBehavior;
import treasure.*;

public class GameEngine {

    private String output; // OneScreen,ShowAll,ShowNone

    private final Dungeon dungeon = Dungeon.getInstance();
    // Example of identity
    // Dungeon is an example of identity. While we could create an instance
    // of dungeon in each character, by having the same instance of dungeon
    // passed to the characters, we can assure that each character
    // has a reference to the same map. This eliminates any issues that may come
    // from specific map generations. We pass its identity to all characters
    // and creatures. Otherwise, we would have an many dungeons of the same
    // type, but not the same identity

    // ArrayLists that contains all Characters, Creatures, and Treasures
    private ArrayList<Character> characterList = new ArrayList<Character>();
    private ArrayList<Creature> creatureList = new ArrayList<Creature>();
    private ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    private CharacterFactory playerFactory = new CharacterFactory(dungeon);
    private CreatureFactory enemyFactory = new CreatureFactory(dungeon);

    private final Tracker tracker = Tracker.getInstance(); // Game Tracker
    // Using the Tracker is an example of the Observer pattern.
    // Events are published to the Tracker (pointed out in comments)
    // And then the Tracker let's any interested parties know about the events.

    private Printer printer; // Game Printer

    private int roundCount = 0; // The Integer round number
    private boolean endCondition = true; // End Condition check
    private final Scanner scanner
        = new java.util.Scanner(System.in); // Scanner for user input

    protected static final int NUMEACH = 4;
    protected static final int MAXTREASURES = 24;


    /**
     * @param outputType String
     *
     * Constructor to initialize board.
     */
    public GameEngine(final String outputType) {
        printer = new Printer(dungeon, tracker, outputType);
        printer.printStartingScreen();
    }


    /**
     * Run the game by simulating processing each turn which includes
     * Characters and Creatures. Ends if the end condition is completed
     */
    public void runGame() {
        populateEntities(dungeon, tracker);
        while (endCondition) {
            roundCount++;
            // publish round counter to Tracker
            tracker.setRoundCount(roundCount);
            processTurn();
        }
        scanner.close();
    }


    /**
     * @param dungeon Dungeon
     * @param tracker Tracker
     *
     * Populate CharacterList and CreatureList with Characters and Creatures
     */
    public void populateEntities(final Dungeon dungeon, final Tracker tracker) {
        // Example of polymorphism.
        // In this case we are adding subclasses to an ArrayList
        // but the ArrayList is made of an abstract class
        // All Characters or Creatures behave as the instance
        // of their abstract class.
        EntityEnum type = EntityEnum.NULL;
        int id = 0; // Object ID value
        boolean nameVal = true;
        String name = new String();
        while (nameVal) {
            System.out.println("Enter your character type "
                + "(Brawler, Thief, Sneaker, Runner): ");
            String stringtype = scanner.nextLine().toUpperCase();
            System.out.println("Enter your name: ");
            name = scanner.nextLine();

            if (stringtype.equals("BRAWLER")) {
                type = EntityEnum.BRAWLER;nameVal = false;
            }
            if (stringtype.equals("THIEF")) {
                type = EntityEnum.THIEF;nameVal = false;
            }
            if (stringtype.equals("SNEAKER")) {
                type = EntityEnum.SNEAKER;nameVal = false;
            }
            if (stringtype.equals("RUNNER")) {
                type = EntityEnum.RUNNER; nameVal = false;
            } else {
                System.out.println("Try again");
                System.out.println();
            }
        }

        // Characters
        characterList.add(playerFactory.createEntity(type, name));
        // publish initial Character stats to Tracker
        tracker.setCharacterStats(characterList);
        // Example of Observer pattern
        // Tracker in turn tells Rooms (subscriber) of new occupancy.

        // Creatures
        // Also an example of polymorphism
        creatureList = enemyFactory.entitySet(4);
        // publish initial Creature stats to Tracker
        tracker.setCreatureStats(creatureList);
        // Again, example of Observer pattern.

        // Treasures
        for (int i = 0; i < NUMEACH; i++) {
            treasureList.add(new Sword(id, dungeon));
            id++;
            treasureList.add(new Gem(id, dungeon));
            id++;
            treasureList.add(new Armor(id, dungeon));
            id++;
            treasureList.add(new Portal(id, dungeon));
            id++;
            treasureList.add(new Trap(id, dungeon));
            id++;
            treasureList.add(new Potion(id, dungeon));
            id++;
        }
        // publish initial Treasure stats to Tracker
        tracker.setTreasureStats(treasureList);
        // Again, example of Observer pattern.
    }


    /**
     * @param character Character
     * @param creature Creature
     *
     * Input a Character and Creature
     * Deducts health if a dice roll is larger than the other.
     *
     * If a character rolls a -1, fight is skipped.
     */
    private void simulateFight(final Character character,
        final Creature creature) {

            // Decorator pattern. Wraps fightbehavior into celebration decorator
            // Fight method is called from the celebration decorator
            FightBehavior fightBehavior = character.getFightBehavior();
            Celebration celebration = new SpinCelebration(fightBehavior);
            celebration = new DanceCelebration(celebration);
            celebration = new JumpCelebration(celebration);
            celebration = new ShoutCelebration(celebration);

            // Called from celebration decorator
            int characterRoll = celebration.fight();
            int creatureRoll = creature.fight();

            for (Treasure t: character.getInventory()) {
                characterRoll += t.getOwnerFightBonus();
                creatureRoll += t.getAdversaryFightBonus();
            }

            if (characterRoll > 0) {
                // If fight not skipped
                if (characterRoll > creatureRoll) {
                    // If Character Wins
                    // Publish Character won and celebration to Tracker
                    tracker.entityWon(character, creature,
                        characterRoll, creatureRoll, "CharacterWon");
                    tracker.characterCelebrated(character, celebration);
                    // Remove dead Creature, publish to Trackers
                    tracker.removeCreature(creature);
                    printer.printFightResults();
                    // Example of Observer pattern
                    // Tracker let's interested parties/subsribers
                    // (Printer, Logger, Room)'s know about these events.
                } else if (characterRoll < creatureRoll) {
                    // If Creature Wins
                    // Publish Creature won to Tracker
                    tracker.entityWon(character, creature,
                        characterRoll, creatureRoll, "CreatureWon");
                    if (character.getHealth() <= 0) {
                        // Remove dead Character, publish to Tracker
                        tracker.removeCharacter(character);
                    }
                    printer.printFightResults();
                }
            } else {
                // If characterRoll = -1, fight was skipped
                tracker.fightSkipped(); // Publish fight skipped to Tracker
                printer.printFightResults();
                // Printer is informed of results to print via the Tracker
                // example of Observer pattern
            }
    }


    /**
     * @param character Character
     *
     * Performs the Character action of searching for treasure.
     * Adds to the Characters treasure count
     */
    private void simulateTreasureHunt(final Character character) {
        int neededScore = character.getSearchBehavior().getNeededScore();
        int score = character.searchTreasure();

        ArrayList<Treasure> treasureInRoom
            = character.getLocation().getTreasuresInRoom();
        if (!treasureInRoom.isEmpty()) {
            // If there is Treasure in the room
            if (score >= neededScore) {
                // If Treasure found
                Treasure currentItem = treasureInRoom.get(0);
                // only find first Treasure (if multiple)
                // If Character has Treasure of first type already
                // but not of second,
                // Character still doesn't get the second Treasure.
                if (character.getInventoryTypes().
                    contains(currentItem.getTreasureType())) {
                    // If we've already encountered this type of Treasure
                    if (currentItem.getTreasureType() == "Trap") {
                        // Can only encounter multiple traps
                        character.addInventory(currentItem);
                        character.loseHealth(currentItem.getTakeDamage());
                        // Publish Treasure found to Tracker
                        tracker.treasureFound(character, currentItem, score);
                        if (character.getHealth() <= 0) {
                            // Remove dead Character, publish to Tracker
                            tracker.removeCharacter(character);
                        }
                    } else {
                        // Publish to Tracker that duplicate item was found
                        tracker.duplicateTreasureFound(character, currentItem,
                            score);
                    }
                } else {
                    // This is a new type of Treasure
                    character.addInventory(currentItem);
                    character.loseHealth(currentItem.getTakeDamage());
                    character.addHealth(currentItem.getHPBoost());
                    // Publish Treasure found to Tracker
                    tracker.treasureFound(character, currentItem, score);
                }
            } else {
                // If Treasure not found
                tracker.treasureNotFound(character, score);
            }
        } else {
            // If no Treasure in room
            tracker.treasureNotFound(character, score);
        }
        printer.printTreasureHuntResults(); // Example of Observer Pattern
        // Printer knows results to print from Tracker
    }


    /**
     * Processes the turns for each Character and for each Creature.
     */
    private void processTurn() {
        Logger logger = Logger.getInstance(); // tracker, output);
        Logger.initializeLogger(tracker, output);
        // Process Characters
        printer.printDungeon();
        for (int i = 0; i < characterList.size(); i++) {
            // Changed to this type of loop to avoid comodification
            Character character =  characterList.get(i);
            if (endCondition) {
                // Stops processing Characters if end condition is met
                process1Character(character); // Process character
                checkWinCondition(); // Updates win conditions}
            } else {
                break;
            }
        }

        // Process Creatures
        for (int i = 0; i < creatureList.size(); i++) {
            // Changing to this type of loop to avoid comodification
            Creature creature = creatureList.get(i);
            if (endCondition) {
                // Stops processing Creatures if end condition is met
                process1Creature(creature);
                checkWinCondition();
            } else {
                break;
            }
        }
        printer.printPause();
        logger.logRound();
    }


    /**
     * @param character Character
     *
     * This method processes the decision making for one Character:
     * - If a Creature is in the Room, it automatically fights.
     * - If no other Creature is in the Room, the Character randomly moves.
     * - In new Room, the Character
     *      searches for treasure if there are no Creatures
     *      or fights if there are Creatures.
     */
    private void process1Character(final Character character) {
        // Process turn counts for characters. Mostly 1 but runners have 2
        for (int i = 0; i < character.getMoveCount(); i++) {
            // Move to new Room
            Room oldRoom = character.getLocation();
            // Check if Character will Blink or RandomWalk
            character.checkPortalInInventory();
            character.move();
            Room newRoom = character.getLocation();
            // Publish Character moved to Tracker
            tracker.characterMoved(character, oldRoom, newRoom);

            // Look for creatures
            ArrayList<Creature> creaturesInRoom = newRoom.getCreaturesInRoom();
            if (creaturesInRoom.size() > 0) {
                // If there are Creatures in the room, fight
                for (int j = 0; j < creaturesInRoom.size(); j++) {
                    Creature creature = creaturesInRoom.get(j);
                    simulateFight(character, creature);
                }
                continue;
            } else {
                // If there are no Creatures in the room, look for treasure
                simulateTreasureHunt(character);
            }
        }
    }


    /**
     * @param creature Creature
     *
     * Processes the decision making for one Creature:
     * - If a Character is in the Room, it automatically fights.
     * - If no other Character is in the Room, move.
     */
    private void process1Creature(final Creature creature) {
        // Get Room information and Characters in the Room
        Room oldRoom = creature.getLocation();

        ArrayList<Character> charactersInOldRoom
            = oldRoom.getCharactersInRoom();
        if (charactersInOldRoom.size() > 0) {
            // If there is a character, don't move, fight!
            for (int i = 0; i < charactersInOldRoom.size(); i++) {
                Character character = charactersInOldRoom.get(i);
                simulateFight(character, creature);
            }
        } else {
            // If no character, move
            creature.move();
            Room newRoom = creature.getLocation();
            // Publish Creature moved to Tracker
            tracker.creatureMoved(creature, oldRoom, newRoom);


            // If characters in new room, fight
            ArrayList<Character> charactersInNewRoom
                = newRoom.getCharactersInRoom();
            for (int i = 0; i < charactersInNewRoom.size(); i++) {
                Character character = charactersInNewRoom.get(i);
                simulateFight(character, creature);
            }
        }
    }


    /**
     * Checks various end game conditions and modifies EndCondition accordingly.
     */
    public void checkWinCondition() {
        int treasureCount = tracker.getTreasureCount();
        int creatureCount = tracker.getCreatureList().size();
        int characterCount = tracker.getCharacterList().size();

        // Change End Condition depending on the outcome
        if (treasureCount == MAXTREASURES) {
            // 24 Treasures Found
            endCondition = false;
            System.out.println();
            System.out.print("Game Over: Round ");
            System.out.println(roundCount);
            System.out.println("All treasure found");
            System.out.println("\n");
        } else if (creatureCount <= 0) {
            //All Creatures eliminated
            endCondition = false;
            System.out.println();
            System.out.print("Game Over: Round ");
            System.out.println(roundCount);
            System.out.println("All Creatures eliminated");
            System.out.println("\n");
        } else if (characterCount <= 0) {
            //All Characters defeated
            endCondition = false;
            System.out.println();
            System.out.print("Game Over: Round ");
            System.out.println(roundCount);
            System.out.println("All Adventurers eliminated");
            System.out.println("\n");
        } else {
            endCondition = true;
        }
        if (!endCondition) {
            try {
                Plotter plotter = new Plotter();
                plotter.plotLineGraph();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
