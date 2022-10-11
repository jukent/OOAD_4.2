package game;

import java.util.ArrayList;
import java.util.HashMap;

import celebration.Celebration;
import dungeon.Room;
import entity.Creature;
import entity.Character;
import treasure.Treasure;

// Example of Observer pattern.
// This Tracker is instantiated at beginning of game and active till end.
// It subscribes for the following published events
// and maintain a data structure in memory for game status:
//  - adventurer and creature enter a room
//  - adventure/creature wins/loses combat
//  - adventurer celebrates
//  - adventurer/creature defeated/removed
//  - treasure found by adventurer

// Subscribers of the Tracker are:
//  Rooms (auto-updates their Character/Creature/Treasure occupancy),
// the Logger,
// and the Printer.

// At first we tried to implement the Observer pattern with
// an Observer interface,
// but are using references in method calls
// Decision informed from "Game Programming Patterns"
// a book by Robert Nystrom http://gameprogrammingpatterns.com/observer.html
// "A more modern approach is for an “observer” to be only a reference to a
// method or function.
// In languages with first-class functions, and especially ones with closures,
// this is a much more common way to do observers.""

public final class Tracker {

    private static ArrayList<Character> characterList; // An ArrayList of
    // active Characters
    private static ArrayList<Creature> creatureList; // An ArrayList of
    // active Creatures
    private static ArrayList<Treasure> treasureList; // An ArrayList of
    // hidden Treasures

    private int roundCount; // Integer Round value
    private int treasureCount; // Integer found Treasure count

    private String fightResult; // Result of the last fight:
    // "CharacterWon", "CreatureWon", or "FightSkipped"
    private HashMap<String, String> fightValues
        = new HashMap<String, String>(); // Ordered HashMap
        // mapping Character and Creature from most recent fight
        // to their integer roll values.
    private Celebration celebration;

    private String treasureHuntResult; // Result of the last trasure hunt:
    // "TreasureFound", "TreasureNotFound", or "DuplicateTreasureFound"
    private HashMap<String, String> treasureHuntValues
        = new HashMap<String, String>(); // HashMap of Treasure and Treasure
        //  roll from most recent treasure hunt.

    // Create a single object for eager Singleton pattern
    private static Tracker instance = new Tracker();


    /**
     * Constructor for the Tracker.
     */
    private Tracker() {
        this.roundCount = 0; // Integer Round count value
        this.treasureCount = 0; // Integer found Treasure count
    }


    /**
     * @return Tracker
     *
     * Gets the only Tracker.
     * Eager Singleton.
     */
    public static Tracker getInstance() {
        return instance;
    }


    /**
     * @return int
     *
     * This returns the Integer Round count value.
     */
    public int getRoundCount() {
        return roundCount;
    }


    /**
     * @param roundCount int
     *
     * This sets the Integer Round count value.
     */
    public void setRoundCount(final int roundCount) {
        this.roundCount = roundCount;
    }


    /**
     * @return int
     *
     * Returns the Integer Treasure count value.
     */
    public int getTreasureCount() {
        return treasureCount;
    }


    /**
     * @param n int
     *
     * Returns the Integer Treasure count value.
     */
    public void setTreasureCount(final int n) {
        this.treasureCount = n;
    }


    /**
     * @param character Character
     * @param room Room
     *
     * Publishes the Character exiting a Room to the Room's occupancy.
     */
    public void publishCharacterExitsRoom(final Character character,
        final Room room) {
            ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
            charactersInRoom.remove(character);
            room.setCharactersInRoom(charactersInRoom);
    }


    /**
     * @param character Character
     * @param room Room
     *
     * Publishes a Character entering a Room to the Room's occupancy.
     */
    public void publishCharacterEntersRoom(final Character character,
        final Room room) {
            ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
            charactersInRoom.add(character);
            room.setCharactersInRoom(charactersInRoom);
    }


    /**
     * @param creature Creature
     * @param room Room
     *
     * Publishes a Creature leaving a Room to the Room's occupancy.
     * Done separately from Characters because it is conventient
     * for the Room's to store Characters and Creatures separately.
     */
    public void publishCreatureExitsRoom(final Creature creature,
        final Room room) {
            ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
            creaturesInRoom.remove(creature);
            room.setCreaturesInRoom(creaturesInRoom);
        }


    /**
     * @param creature Creature
     * @param room Room
     *
     * Publishes a Creature Entering a Room to the Room's occupancy.
     */
    public void publishCreatureEntersRoom(final Creature creature,
        final Room room) {
            ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
            creaturesInRoom.add(creature);
            room.setCreaturesInRoom(creaturesInRoom);
    }


    /**
     * @param treasure Treasure
     * @param room Room
     *
     * Publishes Treasure being removed from a Room to the Room's occupancy.
     */
    public void publishTreasureExitsRoom(final Treasure treasure,
        final Room room) {
            ArrayList<Treasure> treasuresInRoom = room.getTreasuresInRoom();
            treasuresInRoom.remove(treasure);
            room.setTreasuresInRoom(treasuresInRoom);
    }


    /**
     * @param treasure Treasure
     * @param room Room
     *
     * Publishes Treasure being hidden in a Room to the Room's occupancy.
     */
    public void publishTreasureInRoom(final Treasure treasure,
        final Room room) {
            ArrayList<Treasure> treasuresInRoom = room.getTreasuresInRoom();
            treasuresInRoom.add(treasure);
            room.setTreasuresInRoom(treasuresInRoom);
    }


    /**
     * @param characterList ArrayList<Character>
     *
     * Sets the initial Character stats for the Tracker
     * and publishes starting Room occupancy.
     */
    public void setCharacterStats(final ArrayList<Character> characterList) {
        setCharacterList(characterList);
        // Publish Character occupancy to rooms
        for (Character c: characterList) {
            Room room = c.getLocation();
            publishCharacterEntersRoom(c, room);
        }
    }


    /**
     * @param creatureList ArrayList<Creature>
     *
     * Sets the initial Creature stats for the Tracker
     * and publishes starting Room occupancy.
     */
    public void setCreatureStats(final ArrayList<Creature> creatureList) {
        setCreatureList(creatureList);
        // Publish Creature occupancy to rooms
        for (Creature c: creatureList) {
            Room room = c.getLocation();
            publishCreatureEntersRoom(c, room);
        }
    }


    /**
     * @param treasureList ArrayList<Treasure>
     *
     * Sets the initial Treasure stats for the Tracker
     * and publishes starting Room occupancy.
     */
    public void setTreasureStats(final ArrayList<Treasure> treasureList) {
        setTreasureList(treasureList);
        // Publish Treasure occupancy to rooms
        for (Treasure t: treasureList) {
            Room room = t.getLocation();
            publishTreasureInRoom(t, room);
        }
    }


    /**
     * @param character Character
     * @param treasure Treasure
     * @param score Integer
     *
     * Published event that Treasure has been found by a Character.
     *
     * Tracker updates the Treasure count and the Room occupancy.
     */
    public void treasureFound(
        final Character character,
        final Treasure treasure,
        final Integer score) {
            setTreasureCount(getTreasureCount() + 1); // Increase counter by one
            Tracker.treasureList.remove(treasure); // remove from treasure list

            // publish to room that treasure no longer there
            Room room  = treasure.getLocation();
            publishTreasureExitsRoom(treasure, room);

            // Expose results for Printer
            treasureHuntResult = "TreasureFound";
            treasureHuntValues.clear();
            treasureHuntValues.put("character", character.getName());
            treasureHuntValues.put("result", treasureHuntResult);
            treasureHuntValues.put("treasure", treasure.getTreasureType());
            treasureHuntValues.put("score", score.toString());
    }


    /**
     * @return ArrayList<Character>
     *
     * Returns the active Character stats.
     */
    public ArrayList<Character> getCharacterList() {
        return characterList;
    }


    /**
     * @return ArrayList<Creature>
     *
     * Returns the active Creature stats.
     */
    public ArrayList<Creature> getCreatureList() {
        return creatureList;
    }


    /**
     * @param character Character
     * @param creature Creature
     * @param characterRoll int
     * @param creatureRoll int
     * @param fightResult String
     *
     * Pubished event that an Entity won the fight.
     *
     * Tracker reduces Creature's health points by 1.
     */
    public void entityWon(
        final Character character,
        final Creature creature,
        final int characterRoll,
        final int creatureRoll,
        final String fightResult) {
            // Expose results for printer subscriber to use
            fightValues.clear();
            fightValues.put("result", fightResult);
            fightValues.put("character", character.getName());
            fightValues.put("characterRoll", String.valueOf(characterRoll));
            fightValues.put("creature", creature.getName());
            fightValues.put("creatureRoll", String.valueOf(creatureRoll));
            if (fightResult.equals("CharacterWon")) {
                creature.loseHealth(1);
            } else if (fightResult.equals("CreatureWon")) {
                character.loseHealth(1);
            }
    }


    /**
     * @param character Character
     * @param celebration Celebration
     *
     * Published even that a Character celebrated.
     */
    public void characterCelebrated(final Character character,
        final Celebration celebration) {
            this.celebration = celebration;
    }


    /**
     * @return Celebration
     *
     * Returns the tracked Celebration.
     */
    public Celebration getCelebration() {
        return this.celebration;
    }


    /**
     * @param character Character
     *
     * Published even that a Character has lost all of its health.
     *
     * Tracker removes the Character from the active Character list
     * and lets Room subscriper know of new occupancy.
     */
    public void removeCharacter(final Character character) {
        Tracker.characterList.remove(character);

        // Publish Occupancy to Rooms
        Room room = character.getLocation();
        publishCharacterExitsRoom(character, room);
    }


    /**
     * @param creature Creature
     *
     * Published event that a Creature has lost all of its health.
     *
     * Tracker removes the Creature from the active Creature list
     * and lets Room subscriber know of new occupancy.
     */
    public void removeCreature(final Creature creature) {
        Tracker.creatureList.remove(creature);

        // Publish Creature Occupancy to Rooms
        Room room = creature.getLocation();
        publishCreatureExitsRoom(creature, room);
    }


    /**
     * @param character Character
     * @param oldRoom Room
     * @param newRoom Room
     *
     * Published even that a Character has moved.
     *
     * Tracker lets Room subscribers know of new occupancy.
     */
    public void characterMoved(final Character character,
        final Room oldRoom, final Room newRoom) {
        // Remove character from old room occupancy
        publishCharacterExitsRoom(character, oldRoom);

        // Add character to new room occupancy
        publishCharacterEntersRoom(character, newRoom);
    }


    /**
     * @param creature Creature
     * @param oldRoom Room
     * @param newRoom Room
     *
     * Published even that a Creature has moved.
     *
     * Tracker lets Room subscribers know of new occupancy.
     */
    public void creatureMoved(final Creature creature,
        final Room oldRoom, final Room newRoom) {
            // Remove creature from old room occupancy
            publishCreatureExitsRoom(creature, oldRoom);

            // Add creature to new room occupancy
            publishCreatureEntersRoom(creature, newRoom);
    }


    /**
     * @return ArrayList<Treasure>
     *
     * Exposes the Tracker's Treasure List.
     */
    public ArrayList<Treasure> getTreasureList() {
        return Tracker.treasureList;
    }


    /**
     * Publishes event that the fight was skipped.
     */
    public void fightSkipped() {
        fightResult = "FightSkipped";
        fightValues.clear();
        fightValues.put("result", fightResult);
    }


    /**
     * @return HashMap<String, String>
     *
     * Exposes the HashMap of Fight Values
     * (who fought, what were their dice rolls, what was the result).
     */
    public HashMap<String, String> getFightValues() {
        return this.fightValues;
    }


    /**
     * @param character Character
     * @param treasure Treasure
     * @param score Integer
     *
     * Publishes the event that the Treasure was already found.
     */
    public void duplicateTreasureFound(
        final Character character,
        final Treasure treasure,
        final Integer score) {
            treasureHuntResult = "DuplicateTreasureFound";
            treasureHuntValues.clear();
            treasureHuntValues.put("character", character.getName());
            treasureHuntValues.put("result", treasureHuntResult);
            treasureHuntValues.put("treasure", treasure.getTreasureType());
            treasureHuntValues.put("score", score.toString());
    }


    /**
     * @param character Character
     * @param score Integer
     *
     * Publishes the event that Treasure was not found.
     */
    public void treasureNotFound(
        final Character character,
        final Integer score) {
            treasureHuntResult = "TreasureNotFound";
            treasureHuntValues.clear();
            treasureHuntValues.put("character", character.getName());
            treasureHuntValues.put("result", treasureHuntResult);
            treasureHuntValues.put("score", score.toString());
    }


    /**
     * @return HashMap<String, String>
     *
     * Exposes the HashMap of Treasure Hunting Values
     * (what was found, what was the dice roll, what was the result).
     */
    public HashMap<String, String> getTreasureHuntValues() {
        return this.treasureHuntValues;
    }


    /**
     * @param creatureList ArrayList<Creature>
     *
     * Sets the Tracker creature list.
     */
    public static void setCreatureList(final ArrayList<Creature> creatureList) {
        Tracker.creatureList = creatureList;
    }


    /**
     * @return ArrayList<Character>
     *
     * Gets the Character List.
     */
    public ArrayList<Character> getCharcterList() {
        return Tracker.characterList;
    }


    /**
     * @param characterList ArrayList<Character>
     *
     * Sets the Character List.
     */
    public static void setCharacterList(final ArrayList<Character> characterList) {
        Tracker.characterList = characterList;
    }


    /**
     * @param treasureList ArrayList<Treasure>
     *
     * Sets teh Treasure List.ß
     */
    private static void setTreasureList(final ArrayList<Treasure> treasureList) {
        Tracker.treasureList = treasureList;
    }


    /**
     * Clears the Tracker lists for testing purposes.
     */
    public static void clear() {
        setCharacterList(new ArrayList<Character>());
        setCreatureList(new ArrayList<Creature>());
        setTreasureList(new ArrayList<Treasure>());
    }
}
