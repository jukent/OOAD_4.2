package factories;

import dungeon.Dungeon;

public abstract class Factory {

    int id = 0;
    Dungeon dungeon;


    /**
     * @param vDungeon Dungeon
     *
     * Constructor for the factory.
     */
    Factory(Dungeon vDungeon) {
        this.dungeon = vDungeon;
    }
}
