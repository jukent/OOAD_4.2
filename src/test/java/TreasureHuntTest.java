import org.junit.Test;


import dungeon.*;
import entity.*;
import entity.Character;
import treasure.Armor;
import treasure.Trap;
import treasure.Treasure;
import game.Tracker;

import java.util.ArrayList;
import org.junit.Assert;

public class TreasureHuntTest {

    private Dungeon dungeon = new Dungeon();
    private ArrayList<Character> characterList = new ArrayList<Character>();
    private ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    private Tracker tracker = Tracker.getInstance();


    @Test
    public void treasureHuntSuccessTest() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);
        characterList.add(character);
        tracker.setCharacterStats(characterList);

        Armor treasure = new Armor(1, dungeon);
        treasure.setLocation(room);
        treasureList.add(treasure);
        tracker.setTreasureStats(treasureList);

        int neededScore = 0;
        int score = character.searchTreasure();
        Assert.assertTrue(score > neededScore);

        ArrayList<Treasure> treasureInRoom
            = character.getLocation().getTreasuresInRoom();
        Assert.assertEquals(1, treasureInRoom.size());
        Treasure currentItem = treasureInRoom.get(0);

        character.addInventory(currentItem);
        tracker.treasureFound(character, currentItem, 12);

        Assert.assertEquals(1, character.getInventory().size());
    }


    @Test
    public void treasureHuntDuplicateSuccessTest() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);

        Armor treasure = new Armor(1, dungeon);
        character.addInventory(treasure);

        Armor treasure2 = new Armor(2, dungeon);
        treasure2.setLocation(room);
        treasureList.add(treasure2);

        Assert.assertTrue(character.getInventoryTypes().
            contains(treasure2.getTreasureType()));
    }


    @Test
    public void treasureHuntDuplicateTrap() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);

        Trap treasure = new Trap(1, dungeon);
        character.addInventory(treasure);

        Trap treasure2 = new Trap(2, dungeon);
        treasure2.setLocation(room);
        treasureList.add(treasure2);
        tracker.setTreasureStats(treasureList);

        if (character.getInventoryTypes().
            contains(treasure2.getTreasureType())) {
                if (treasure2.getTreasureType() == "Trap") {
                    character.addInventory(treasure2);
                    tracker.treasureFound(character, treasure2, 12);
                }
        }
        Assert.assertEquals(2, character.getInventory().size());
        Assert.assertEquals("Trap, Trap", character.getInventoryString());
    }


    @Test
    public void carelessTreasureHuntTest() {
        Brawler brawler = new Brawler(0, dungeon);
        Assert.assertEquals("Careless",
            brawler.getSearchBehavior().getSearchType());
    }


    @Test
    public void carefulTreasureHuntTest() {
        Thief thief = new Thief(0, dungeon);
        Assert.assertEquals("Careful",
            thief.getSearchBehavior().getSearchType());
    }


    @Test
    public void quickTreasureHuntTest() {
        Runner runner = new Runner(0, dungeon);
        Assert.assertEquals("Quick",
            runner.getSearchBehavior().getSearchType());
    }
}
