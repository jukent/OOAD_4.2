package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class RunnerFactory extends CharacterFactory {
    
    //Factory Pattern Subclasses
    public RunnerFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Character spawnCharacter(String Name){
        return new Runner(this.id, this.dungeon,Name);
    }


}
