package entity;

import dungeon.Dungeon;
import fight.StealthyFightBehavior;
import treasurehunt.QuickHuntBehavior;

public class Sneaker extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     * @param name String
     *
     * Construct Sneakers with an Integer ID `id`, the Dungeon, and a name.
     */
    public Sneaker(final int id, final Dungeon map, final String name) {
        setID(id); // Sneaker ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Begin in Entrance Room
        setFightBehavior(new StealthyFightBehavior()); // FightType is Stealthy
        setSearchBehavior(new QuickHuntBehavior()); // SearchType is Quick
        setName(name); // Sring name
        setTitle("Sneaker");
        setType(EntityEnum.SNEAKER);
        setHP(8);
    }
}
