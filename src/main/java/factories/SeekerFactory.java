package factories;

import entity.*;
import dungeon.Dungeon;


public class SeekerFactory extends CreatureFactory{
       
    //Factory Pattern Subclasses
    public SeekerFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Creature spawnCreature(){
        return new Seeker(this.id, this.dungeon);
    }
}
