package factories;

import entity.Creature;
import dungeon.Dungeon;

public abstract class CreatureFactory extends Factory {

    //Factory pattern abstract class
    public CreatureFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public abstract Creature spawnCreature();

}
