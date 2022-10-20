package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class SneakerFactory extends CharacterFactory {
    
    public SneakerFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Character spawnCharacter(String Name){
        return new Sneaker(this.id, this.dungeon,Name);
    }


}