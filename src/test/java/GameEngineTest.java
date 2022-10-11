import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import game.Tracker;
import game.GameEngine;
import entity.Creature;
import entity.Character;

public class GameEngineTest {

    protected static final int CHARNUMBER = 4;
    protected static final int CREATNUMBER = 12;
    protected static final int TREASURENUMBER = 24;


    @Test
    public void testPopulateEntities() {
        GameEngine gameEngine = new GameEngine("ShowNone");

        Dungeon dungeon = new Dungeon();
        Tracker tracker = Tracker.getInstance();
        Tracker.clear();

        gameEngine.populateEntities(dungeon, tracker);

        Assert.assertEquals(CHARNUMBER, tracker.getCharacterList().size());
        Assert.assertEquals(CREATNUMBER, tracker.getCreatureList().size());
        Assert.assertEquals(TREASURENUMBER, tracker.getTreasureList().size());
    }


    @Test
    public void testEndConditionAllTreasureFound() {
        GameEngine gameEngine = new GameEngine("ShowNone");
        Boolean endCondition = true;

        Dungeon dungeon = new Dungeon();
        Tracker tracker = Tracker.getInstance();
        Tracker.clear();

        gameEngine.populateEntities(dungeon, tracker);
        Assert.assertTrue(endCondition);

        tracker.setTreasureCount(TREASURENUMBER);

        if (tracker.getTreasureCount() == TREASURENUMBER) {
            endCondition = false;
        }
        if (tracker.getCreatureList().size() == 0) {
            endCondition = true;
        }
        if (tracker.getCharacterList().size() == 0) {
            endCondition = true;
        }
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCreaturesDefeated() {
        GameEngine gameEngine = new GameEngine("ShowNone");
        Boolean endCondition = true;

        Dungeon dungeon = new Dungeon();
        Tracker tracker = Tracker.getInstance();
        Tracker.clear();

        gameEngine.populateEntities(dungeon, tracker);
        Assert.assertTrue(endCondition);

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Tracker.setCreatureList(creatureList);

        if (tracker.getTreasureCount() == 24) {
            endCondition = true;
        }
        if (tracker.getCreatureList().size() <= 0) {
            endCondition = false;
        }
        if (tracker.getCharacterList().size() <= 0) {
            endCondition = true;
        }
        Assert.assertEquals(TREASURENUMBER, tracker.getTreasureList().size());
        Assert.assertEquals(0, tracker.getCreatureList().size());
        Assert.assertEquals(CHARNUMBER, tracker.getCharacterList().size());
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCharactersDefeated() {
        GameEngine gameEngine = new GameEngine("ShowNone");
        Boolean endCondition = true;

        Dungeon dungeon = new Dungeon();
        Tracker tracker = Tracker.getInstance();
        Tracker.clear();

        gameEngine.populateEntities(dungeon, tracker);
        Assert.assertTrue(endCondition);

        ArrayList<Character> characterList = new ArrayList<Character>();
        Tracker.setCharacterList(characterList);

        if (tracker.getTreasureCount() == 24) {
            endCondition = true;
        }
        if (tracker.getCreatureList().size() == 0) {
            endCondition = true;
        }
        if (tracker.getCharacterList().size() == 0) {
            endCondition = false;
        }
        Assert.assertEquals(TREASURENUMBER, tracker.getTreasureList().size());
        Assert.assertEquals(CREATNUMBER, tracker.getCreatureList().size());
        Assert.assertEquals(0, tracker.getCharacterList().size());
        Assert.assertFalse(endCondition);
    }
}
