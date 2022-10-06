import org.junit.Test;
import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Orbiter;

public class OrbitMovementTest {

    private Dungeon dungeon = new Dungeon();


    @Test
    public void testOrbitClockwise() {
        Orbiter orbiter = new Orbiter(0, dungeon);
        orbiter.setDirection("clockwise");

        Assert.assertEquals("clockwise", orbiter.getDirection());

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        orbiter.setLocation(oldRoom);

        Assert.assertEquals(orbiter.getLocation(), oldRoom);

        Assert.assertEquals("Orbit", orbiter.getMovementType());

        orbiter.move();
        Room newRoom = orbiter.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
        Assert.assertEquals("(1-0-1)", newRoom.getName());
    }


    @Test
    public void testOrbitCounterClockwise() {
        Orbiter orbiter = new Orbiter(0, dungeon);
        orbiter.setDirection("counterclockwise");

        Assert.assertEquals("counterclockwise", orbiter.getDirection());

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        orbiter.setLocation(oldRoom);

        Assert.assertEquals(orbiter.getLocation(), oldRoom);

        Assert.assertEquals("Orbit", orbiter.getMovementType());

        orbiter.move();
        Room newRoom = orbiter.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
        Assert.assertEquals("(1-1-0)", newRoom.getName());
    }
}
