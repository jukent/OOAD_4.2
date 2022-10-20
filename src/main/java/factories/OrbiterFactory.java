package factories;

import entity.*;
import dungeon.Dungeon;


public class OrbiterFactory extends CreatureFactory {


    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Orbiter factory.
     */
    public OrbiterFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CreatureFactory#spawnCreature()
     */
    public Creature spawnCreature() {
        return new Blinker(this.id, this.dungeon);
    }
}
