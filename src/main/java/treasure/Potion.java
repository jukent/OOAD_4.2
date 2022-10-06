package treasure;

import dungeon.Dungeon;

public class Potion extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for a Potion Treasure with Integer ID `id` and the Dungeon.
     */
    public Potion(final int id, final Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        setHPBoost(1); // Potions restore 1 health point
        setTreasureType("Potion"); // String name
    }
}
