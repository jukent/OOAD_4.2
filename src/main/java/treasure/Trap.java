package treasure;

import dungeon.Dungeon;

public class Trap extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for a Trap treasure with Integer ID `id` and the Dungeon.
     */
    public Trap(final int id, final Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        setTakeDamage(1); // Traps do 1 point of damage
        setTreasureType("Trap"); // String name
    }
}
