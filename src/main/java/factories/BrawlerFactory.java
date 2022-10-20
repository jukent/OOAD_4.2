package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class BrawlerFactory extends CharacterFactory {
    
    //Factory Pattern Subclasses
    public BrawlerFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public Character spawnCharacter(String Name){
        return new Brawler(this.id, this.dungeon,Name);
    }

}
