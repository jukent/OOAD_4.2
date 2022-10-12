import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Creature;
import entity.Seeker;
import entity.Brawler;
import entity.Character;
import game.Tracker;

public class MovementBehaviorTest {

    private Dungeon dungeon = Dungeon.getInstance();


    @Test
    public void testStayWithCharacter() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler character = new Brawler(0, dungeon,"");
        characterList.add(character);

        Room characterRoom = dungeon.getRoom("(1-0-0)");
        character.setLocation(characterRoom);

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker seeker = new Seeker(1, dungeon);
        creatureList.add(seeker);

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        seeker.setLocation(oldRoom);

        Assert.assertEquals(seeker.getLocation(), oldRoom);
        Assert.assertEquals(oldRoom, characterRoom);

        Tracker tracker = Tracker.getInstance();
        tracker.setCharacterStats(characterList);
        tracker.setCreatureStats(creatureList);

        seeker.move();
        Room newRoom = seeker.getLocation();
        tracker.creatureMoved(seeker, oldRoom, newRoom);

        Assert.assertEquals(oldRoom, newRoom);
        Assert.assertEquals(characterRoom, newRoom);
    }
}
