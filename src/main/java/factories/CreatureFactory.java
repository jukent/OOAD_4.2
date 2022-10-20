package factories;

import entity.Creature;
import dungeon.Dungeon;

public abstract class CreatureFactory extends Factory {


    //Factory pattern abstract class
    /**
     * @param dungeonRef Dungeon
     *
     * Creature Factory constructor.
     */
    public CreatureFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /**
     * @return Creature
     *
     * Abstract method to spawn Creatures.
     */
    public abstract Creature spawnCreature();
}
