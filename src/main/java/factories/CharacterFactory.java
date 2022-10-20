package factories;

import entity.Character;
import dungeon.Dungeon;

public abstract class CharacterFactory extends Factory {


    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Character factory.
     */
    public CharacterFactory(Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /**
     * @param title String
     * @return Character
     *
     * Abstract method to spawn Characters.
     */
    public abstract Character spawnCharacter(String title);
}
