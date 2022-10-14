import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Brawler;

public class RandomWalkMovementTest {

    private Dungeon dungeon = Dungeon.getInstance();


    @Test
    public void testRandomWalk() {
        Brawler character = new Brawler(0, dungeon, "");
        Room oldRoom = dungeon.getRoom("(1-0-0)");
        character.setLocation(oldRoom);

        Assert.assertEquals(character.getLocation(), oldRoom);

        Assert.assertEquals("RandomWalk", character.getMovementType());

        character.move();
        Room newRoom = character.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);

        // check there is exit from oldRoom to newRoom
        ArrayList<String> exits = oldRoom.getExits();
        Assert.assertTrue(exits.contains(newRoom.getName()));
    }
}
