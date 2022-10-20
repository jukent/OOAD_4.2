package factories;

import entity.*;
import dungeon.Dungeon;


public class OrbiterFactory extends CreatureFactory{
       
    public OrbiterFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Creature spawnCreature(){
        return new Blinker(this.id, this.dungeon);
    }
}
