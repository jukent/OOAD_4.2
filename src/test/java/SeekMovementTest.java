import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Creature;
import entity.Seeker;
import entity.Brawler;
import entity.Character;
import game.Tracker;

public class SeekMovementTest {

    private Dungeon dungeon = Dungeon.getInstance();


    @Test
    public void testAwaitCharacter() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker seeker = new Seeker(1, dungeon);
        creatureList.add(seeker);

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        seeker.setLocation(oldRoom);

        Assert.assertEquals(seeker.getLocation(), oldRoom);

        Tracker tracker = Tracker.getInstance();
        tracker.setCreatureStats(creatureList);

        Assert.assertEquals("Seek", seeker.getMovementType());

        seeker.move();
        Room newRoom = seeker.getLocation();
        tracker.creatureMoved(seeker, oldRoom, newRoom);

        Assert.assertEquals(oldRoom, newRoom);
    }


    @Test
    public void testMove2Character() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler character = new Brawler(0, dungeon, "");
        characterList.add(character);

        Room characterRoom = dungeon.getRoom("(2-0-0)");
        character.setLocation(characterRoom);

        Assert.assertEquals(character.getLocation(), characterRoom);

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker seeker = new Seeker(1, dungeon);
        creatureList.add(seeker);

        Room oldRoom = dungeon.getRoom("(2-1-0)");
        seeker.setLocation(oldRoom);

        Assert.assertEquals(seeker.getLocation(), oldRoom);

        Assert.assertNotEquals(oldRoom, characterRoom);

        // check there is exit to characterRoom
        Collection<String> exitsCol = oldRoom.getExits().values();
        ArrayList<String> exits = new ArrayList<String>(exitsCol);
        Assert.assertTrue(exits.contains(characterRoom.getName()));

        Tracker tracker = Tracker.getInstance();
        tracker.setCharacterStats(characterList);
        tracker.setCreatureStats(creatureList);

        // check that we can get characters in room
        ArrayList<Character> charactersInRoom
            = characterRoom.getCharactersInRoom();
        Assert.assertEquals(charactersInRoom, characterList);

        Assert.assertEquals("Seek", seeker.getMovementType());

        seeker.move();
        Room newRoom = seeker.getLocation();
        tracker.creatureMoved(seeker, oldRoom, newRoom);

        Assert.assertNotEquals(seeker.getLocation(), oldRoom);
        Assert.assertEquals(characterRoom, newRoom);
    }
}
