package entity;

import dungeon.Room;

public abstract class Creature extends Entity {


    /**
     * @return Room
     *
     * The abstract method determines a Creature's starting room
     * to be overwritten with more specific Creature behavior.
     */
    abstract Room pickStartingRoom();
}
