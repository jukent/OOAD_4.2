package factories;

import entity.*;
import dungeon.Dungeon;


public class SeekerFactory extends CreatureFactory {


    //Factory Pattern Subclasses
    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Seeker factory.
     */
    public SeekerFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CreatureFactory#spawnCreature()
     */
    public Creature spawnCreature() {
        return new Seeker(this.id, this.dungeon);
    }
}
