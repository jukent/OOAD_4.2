package factories;

import entity.*;
import entity.Character;
import dungeon.Dungeon;

public class ThiefFactory extends CharacterFactory {

    
    //Factory Pattern Subclasses
    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the thief factory.
     */
    public ThiefFactory(final Dungeon dungeonRef) {
        super(dungeonRef);
    }


    /* (non-Javadoc)
     * @see factories.CharacterFactory#spawnCharacter(java.lang.String)
     */
    public Character spawnCharacter(String name)  {
        return new Thief(this.id, this.dungeon, name);
    }
}
