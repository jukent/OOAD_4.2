package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class SneakerFactory extends CharacterFactory {


    //Factory Pattern Subclasses
    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Sneaker factory.
     */
    public SneakerFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CharacterFactory#spawnCharacter(java.lang.String)
     */
    public Character spawnCharacter(final String name) {
        return new Sneaker(this.id, this.dungeon, name);
    }
}
