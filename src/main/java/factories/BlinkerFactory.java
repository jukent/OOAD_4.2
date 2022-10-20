package factories;

import entity.*;
import dungeon.Dungeon;

public class BlinkerFactory extends CreatureFactory{
   
    //Factory Pattern Subclasses
    public BlinkerFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Creature spawnCreature(){
        return new Blinker(this.id, this.dungeon);
    }
}
