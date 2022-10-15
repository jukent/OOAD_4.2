import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Blinker;

public class BlinkMovementTest {

    private Dungeon dungeon = Dungeon.getInstance();

    protected static final int MAXLEVEL = 4;

    @Test
    public void testCreatureBlink() {
        Blinker blinker = new Blinker(0, dungeon);

        Room oldRoom = blinker.getLocation();

        int level = oldRoom.getLevel();
        Assert.assertEquals(MAXLEVEL, level);

        Assert.assertEquals("Blink", blinker.getMovementType());

        blinker.move();
        Room newRoom = blinker.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
    }
}
