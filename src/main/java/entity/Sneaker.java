package entity;

import dungeon.Dungeon;
import fight.StealthyFightBehavior;
import treasurehunt.QuickHuntBehavior;

public class Sneaker extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Construct Sneakers with an Integer ID `id` and the Dungeon
     */
    public Sneaker(final int id, final Dungeon map, final String Name) {
        setID(id); // Sneaker ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Begin in Entrance Room
        setFightBehavior(new StealthyFightBehavior()); // FightType is Stealthy
        setSearchBehavior(new QuickHuntBehavior()); // SearchType is Quick
        setName("Sneaker"); // Sring name
        setTitle(Name);
        setType(EntityEnum.SNEAKER);
        setHP(8);
    }
}
