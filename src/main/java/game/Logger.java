package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import entity.Creature;
import treasure.Treasure;
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
     * @return HashMap<String, Object> 
     *
     * This method returns a HashMap of Character
     * name, location, damage, and treasures.
     */
    private HashMap<String, Object> getCharacterDict() {
        Character character = tracker.getCharacterList().get(0);
        // Later this won't be from a list because ther is just one character
        HashMap<String,Object> characterDict = new HashMap<String,Object>();
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
        HashMap<String,Object> creatureDict = new HashMap<String,Object>();
        creatureDict.put("Remaining", tracker.getCreatureList().size());
        for (Creature creature: tracker.getCreatureList()) {
            creatureDict.put(creature.getName(), creature.getLocation().getName());
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
            HashMap<String, Object> logDict = new HashMap<String,Object>();

            int roundCount = tracker.getRoundCount();
            logDict.put("Turn", roundCount);

            logDict.put("Character", getCharacterDict());
            logDict.put("Creatures", getCreatureDict());

            JSONObject jsonLog = new JSONObject(logDict);
            try {
                String roundCountPadded = String.format("%03d", roundCount);
                String fileName
                    = new String("Logger-files/Logger-" + roundCountPadded + ".json");
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(jsonLog.toJSONString());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred. Could not write file.");
                e.printStackTrace();
            }
        }
    }
}
