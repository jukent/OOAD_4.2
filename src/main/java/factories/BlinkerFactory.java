package factories;

import entity.*;
import dungeon.Dungeon;

public class BlinkerFactory extends CreatureFactory {


    //Factory Pattern Subclasses
    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Blinker factory.
     */
     public BlinkerFactory(final Dungeon dungeonRef){
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CreatureFactory#spawnCreature()
     */
    public Creature spawnCreature() {
        return new Blinker(this.id, this.dungeon);
    }
}
