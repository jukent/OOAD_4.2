package factories;

import dungeon.Dungeon;


public abstract class Factory {

    int id = 0;
    Dungeon dungeon;

 
    //Factory Pattern BaseClass to be split into creature and character classes
    /**
     * @param vDungeon Dungeon
     *
     * Constructor for the factory.
     */
    Factory(Dungeon vDungeon) {
        this.dungeon = vDungeon;
    }
}
