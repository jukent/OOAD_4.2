package game;
import entity.*;
import dungeon.*;

public abstract class Factory {
    protected Dungeon dungeonRef;
    protected int ID = 0;
    
    abstract Entity CreateEntity(EntityEnum type, String Title);



}
