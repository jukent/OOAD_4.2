package factories;

import entity.*;
import dungeon.Dungeon;


public class SeekerFactory extends CreatureFactory {


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
        return new Blinker(this.id, this.dungeon);
    }
}
