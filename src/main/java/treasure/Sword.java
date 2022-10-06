package treasure;

import dungeon.Dungeon;

public class Sword extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for a Sword Treasure with Integer ID `id` and the Dungeon.
     */
    public Sword(final int id, final Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        setOwnerFightBonus(1); // Swords give a Character strength buff of 1
        setTreasureType("Sword"); // String name
    }
}
