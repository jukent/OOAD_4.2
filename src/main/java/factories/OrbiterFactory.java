package factories;

import entity.*;
import dungeon.Dungeon;


public class OrbiterFactory extends CreatureFactory {


    //Factory Pattern Subclasses
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
        return new Orbiter(this.id, this.dungeon);
    }
}
