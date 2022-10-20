package factories;

import entity.Character;
import dungeon.Dungeon;

public abstract class CharacterFactory extends Factory{
    
    public CharacterFactory(Dungeon dungeonRef){
        super(dungeonRef);
    }

    public abstract Character spawnCharacter(String title);
}
