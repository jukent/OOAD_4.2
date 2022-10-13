
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import treasure.*;


public class TreasureBonusTest {
    private Dungeon dungeon = Dungeon.getInstance();
    private Character runner = new Runner(1, dungeon, "");
    private Treasure potion = new Potion(1, dungeon);
    private Treasure armor = new Armor(1, dungeon);
    private Treasure gem = new Gem(1, dungeon);
    private Treasure sword = new Sword(1, dungeon);
    private Treasure trap = new Trap(1, dungeon);

    protected static final int MAXHEALTH = 4;


    @Test
    public void testBonuses() {
        Assert.assertEquals(1, potion.getHPBoost());
        Assert.assertEquals(-1, armor.getAdversaryFightBonus());
        Assert.assertEquals(1, gem.getAdversaryFightBonus());
        Assert.assertEquals(1, sword.getOwnerFightBonus());
    }


    @Test
    public void testHPAddition() {
        runner.addHealth(potion.getHPBoost());
        Assert.assertEquals(MAXHEALTH, runner.getHealth());
    }


    @Test
    public void testTrap() {
        runner.loseHealth(trap.getTakeDamage());
        Assert.assertEquals(2, runner.getHealth());
    }
}
