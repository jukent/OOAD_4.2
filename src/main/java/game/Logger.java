package game;

import java.io.FileWriter;
import java.io.IOException;

import entity.Creature;
import entity.Character;


// Example of Observer Pattern

// Logger Class to be instantiated at the beginning of each full
// adventurer/creature turn (not individual)
// and closes at the end of each turn.
// Logs results to "Logger-n.txt" where n is the turn of the simulation.
// Logged values are stored/managed/updated by the Tracker
// whenever a relevant event is published.

public class Logger {

    private Tracker tracker; // The Game Tracker
    private String outputType; // String output options

    protected static final int MAXHEALTH = 3;


    /**
     * @param tracker Tracker
     * @param outputType String
     *
     * Constructor for the turn Logger.
     */
    public Logger(final Tracker tracker, final String outputType) {
        this.tracker = tracker; // The Game Tracker
        this.outputType =
            outputType; // Output options:
            // OneScreen, ShowAll, ShowEnding, ShowNone
    }



    /**
     * @param fileWriter FileWriter
     *
     * This method logs Character stats: name, location, damage, and treausres.
     */
    private void logCharacterStats(final FileWriter fileWriter) {
        String tableHeader = new String("Adventurers\tRoom\tDamage\tTreasure");
        try {
            fileWriter.write("\n");
            fileWriter.write(tableHeader);

            fileWriter.write("\n");

            for (Character c: tracker.getCharacterList()) {
                String name = c.getName();
                String location = c.getLocation().getName();
                Integer damage = MAXHEALTH - c.getHealth();
                String treasure = c.getInventoryString();

                String characterStats =
                    new String(name + "\t\t" + location + "\t"
                    + damage + "\t\t" + treasure);
                fileWriter.write(characterStats);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.
                println("An error occurred. Could not write Character stats.");
            e.printStackTrace();
        }
    }


    /**
     * @param fileWriter FileWriter
     *
     * This method logs Creature stats: name and location.
     */
    private void logCreatureStats(final FileWriter fileWriter) {
        try {
            fileWriter.write("\n");
            int totalCreatures = tracker.getCreatureList().size();
            fileWriter.write("Total Active Creatures: " + totalCreatures);
            fileWriter.write("\n");

            fileWriter.write("\n");
            String tableHeader = new String("Creatures\tRoom");
            fileWriter.write(tableHeader);
            fileWriter.write("\n");

            for (Creature c: tracker.getCreatureList()) {
                String name = c.getName();
                String room = c.getLocation().getName();
                String creatureStats = new String(name + "\t\t" + room);
                fileWriter.write(creatureStats);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.
                println("An error occurred. Could not write Creature stats.");
            e.printStackTrace();
        }
    }


    /**
     * The method logs all required components for each round
     * (Character stats and Creature stats).
     */
    public void logRound() {
        if (outputType != "ShowNone") {
        // Don't produce Logs for multiple game runs with "ShowNone" set
            int roundCount = tracker.getRoundCount();

            String fileName
                = new String("Logger-files/Logger-" + roundCount + ".txt");

            try {
                FileWriter fileWriter = new FileWriter(fileName);

                String roundString = new String("Tracker: Turn " + roundCount);
                fileWriter.write(roundString);
                fileWriter.write("\n");

                logCharacterStats(fileWriter);
                logCreatureStats(fileWriter);

                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred. Could not write file.");
                e.printStackTrace();
            }
        }
    }
}
