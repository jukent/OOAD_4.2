package game;
import entity.*;
import dungeon.*;

public abstract class Factory {
    protected Dungeon dungeonRef;
    protected int id = 0;


    /**
     * @param type EntityEnum
     * @param title String
     * @return Entity
     *
     * Abstract method for creating Entities.
     */
    abstract public Entity createEntity(EntityEnum type, String title);
}
