
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import fight.*;
import celebration.*;


public class CelebrationTest {
    private Dungeon dungeon = Dungeon.getInstance();
    private Character character = new Runner(1, dungeon);
    private FightBehavior fightBehavior = character.getFightBehavior();


    @Test
    public void testWrap() {
        Celebration tJ = new JumpCelebration(fightBehavior);
        tJ = new SpinCelebration(tJ);
        Assert.assertEquals("Untrained", tJ.getFightRef().getFightType());
    }
}
