package factories;

import entity.Creature;
import dungeon.Dungeon;

public abstract class CreatureFactory extends Factory {

    public CreatureFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public abstract Creature spawnCreature();

}
