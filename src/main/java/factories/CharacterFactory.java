package factories;

import entity.Character;
import dungeon.Dungeon;

public abstract class CharacterFactory extends Factory{
    
    //Factory pattern abstract class
    public CharacterFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public abstract Character spawnCharacter(String title);
}
