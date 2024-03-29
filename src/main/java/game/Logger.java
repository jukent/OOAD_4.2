package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

import entity.Creature;
import treasure.Treasure;
import entity.Character;

// Example of Lazy Singleton Pattern
// The Logger has a private constructor and can be accessed via a
// `getInstance()` method to ensure that there is only one Logger
// holding information of the game data even though it is called
// every turn.
// This is a Lazy Singleton pattern because the Logger isn't created
// until the first time `getInstance()` is called. This is why there is
// an if-check to see if the Logger already exists before the return
// in the `getInstance()` method.


// Example of Observer Pattern

// Logger Class to be called at the beginning of each full
// adventurer/creature turn (not individual)
// and closes at the end of each turn.
// Logs results to "Logger-n.txt" where n is the turn of the simulation.
// Logged values are stored/managed/updated by the Tracker
// whenever a relevant event is published.

public final class Logger {

    private static Tracker tracker; // The Game Tracker
    private static String outputType; // String output options

    protected static final int MAXHEALTH = 3;

    // Create a single object for lazy Singleton pattern
    private static Logger instance;


    /**
     * Constructor for the turn Logger.
     *
     * Making constructor private so that this class cannot be
     * instantiated.
     */
    private Logger() { }


    /**
     * @return Logger
     *
     * Gets the only Logger.
     * Lazy Singleton.
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }


    /**
     * @param tracker Tracker
     * @param outputType String
     *
     * Initializes the Logger with the GameTracker and the output type String.
     *
     * Necessary for Singleton Pattern becasue Logger cannot have parameters,
     * but we want to pass certain information into it.
     */
    public static void initializeLogger(final Tracker tracker,
        final String outputType) {
            Logger.tracker = tracker; // The Game Tracker
            Logger.outputType = outputType; // Output options:
            // OneScreen, ShowAll, ShowEnding, ShowNone)
    }



    /**
     * @return HashMap<String, Object>
     *
     * This method returns a HashMap of Character
     * name, location, damage, and treasures.
     */
    private HashMap<String, Object> getCharacterDict() {
        Character character = tracker.getCharacterList().get(0);
        // Later this won't be from a list because ther is just one character
        HashMap<String, Object> characterDict = new HashMap<String, Object>();
        characterDict.put("Name", character.getName());
        characterDict.put("Type", character.getClass().getSimpleName());
        characterDict.put("Location", character.getLocation().getName());
        characterDict.put("Health", character.getHealth());
        ArrayList<Treasure> treasureObjs = character.getInventory();
        ArrayList<String> treasureStrs = new ArrayList<String>();
        for (Treasure t: treasureObjs) {
            treasureStrs.add(t.getTreasureType());
        }
        characterDict.put("Treasure", treasureStrs);
        return characterDict;
    }


    /**
     * @return HashMap<String, Object>
     *
     * This method returns a Hashmap of Creature stats:
     * name and location.
     */
    private HashMap<String, Object> getCreatureDict() {
        HashMap<String, Object> creatureDict = new HashMap<String, Object>();
        creatureDict.put("Remaining", tracker.getCreatureList().size());
        for (Creature creature: tracker.getCreatureList()) {
            creatureDict.put(creature.getName(),
                creature.getLocation().getName());
        }
        return creatureDict;
    }


    /**
     * The method logs all required components for each round
     * (Character stats and Creature stats).
     *
     * Changing this from the method in project 3.2 (formatted
     * .txt file) to a .json file because the json file is easier
     * to grab data from,
     * which we have to do for the Java line graph.
     *
     * Help from TutorialsPoint "How to Write/Create a JSON File Using Java":
     * https://www.tutorialspoint.com/how-to-write-create-a-json-file-using-java
     */
    public void logRound() {
        if (outputType != "ShowNone") {
        // Don't produce Logs for multiple game runs with "ShowNone" set
            HashMap<String, Object> logDict = new HashMap<String, Object>();

            int roundCount = tracker.getRoundCount();
            logDict.put("Turn", roundCount);

            logDict.put("Character", getCharacterDict());
            logDict.put("Creatures", getCreatureDict());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = gson.toJson(logDict);
            try {
                String roundCountPadded = String.format("%03d", roundCount);
                String fileName
                    = new String("Logger-files/Logger-"
                    + roundCountPadded + ".json");
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(prettyJsonString);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred. Could not write file.");
                e.printStackTrace();
            }
        }
    }
}
