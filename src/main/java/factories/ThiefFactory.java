package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class ThiefFactory extends CharacterFactory {
    
    //Factory Pattern Subclasses
    public ThiefFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Character spawnCharacter(String Name){
        return new Thief(this.id, this.dungeon,Name);
    }


}
