package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class BrawlerFactory extends CharacterFactory {



    //Factory Pattern Subclasses
    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Brawler factory.
     */
     public BrawlerFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CharacterFactory#spawnCharacter(java.lang.String)
     */
    public Character spawnCharacter(String name) {
        return new Brawler(this.id, this.dungeon, name);
    }
}
