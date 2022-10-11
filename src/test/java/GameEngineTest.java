import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import game.Tracker;
import game.GameEngine;

public class GameEngineTest {

    private GameEngine gameEngine = new GameEngine("ShowNone");
    private Boolean endCondition = true;

    private Dungeon dungeon = new Dungeon();
    private Tracker tracker = Tracker.getInstance();

    protected static final int CHARNUMBER = 4;
    protected static final int CREATNUMBER = 12;
    protected static final int TREASURENUMBER = 24;


    @Test
    public void testPopulateEntities() {
        gameEngine.populateEntities(this.dungeon, this.tracker);

        Assert.assertEquals(CHARNUMBER, tracker.getCharacterList().size());
        Assert.assertEquals(CREATNUMBER, tracker.getCreatureList().size());
        Assert.assertEquals(TREASURENUMBER, tracker.getTreasureList().size());
    }


    @Test
    public void testEndConditionAllTreasureFound() {
        Assert.assertTrue(endCondition);

        tracker.setTreasureCount(TREASURENUMBER);

        if (tracker.getTreasureCount() == TREASURENUMBER) {
            endCondition = false;
        } else if (tracker.getCreatureList().size() == 0) {
            endCondition = true;
        } else if (tracker.getCharacterList().size() == 0) {
            endCondition = true;
        }
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCreaturesDefeated() {
        Assert.assertTrue(endCondition);
        gameEngine.populateEntities(dungeon, tracker);

        tracker.getCreatureList().removeAll(tracker.getCreatureList());

        if (tracker.getTreasureCount() == 24) {
            endCondition = true;
        } else if (tracker.getCreatureList().size() == 0) {
            endCondition = false;
        } else if (tracker.getCharacterList().size() == 0) {
            endCondition = true;
        }
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCharactersDefeated() {
        Assert.assertTrue(endCondition);
        gameEngine.populateEntities(dungeon, tracker);

        tracker.getCharacterList().removeAll(tracker.getCharacterList());

        if (tracker.getTreasureCount() == 24) {
            endCondition = true;
        } else if (tracker.getCreatureList().size() == 0) {
            endCondition = true;
        } else if (tracker.getCharacterList().size() == 0) {
            endCondition = false;
        }
        Assert.assertFalse(endCondition);
    }
}
