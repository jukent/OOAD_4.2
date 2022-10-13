package entity;

import dungeon.Dungeon;
import fight.TrainedFightBehavior;
import treasurehunt.CarefulHuntBehavior;

public class Thief extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     * @param name String
     *
     * Construct Thieves with an Integer ID `id, the Dungeon, and a name.
     */
    public Thief(final int id, final Dungeon map, final String name) {
        setID(id); // Thief ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Start in Entrance Room
        setFightBehavior(new TrainedFightBehavior()); // FightType is Trained
        setSearchBehavior(new CarefulHuntBehavior()); // SearhType is Careful
        setName("Thief"); // String name
        setTitle(name);
        setType(EntityEnum.THIEF);
        setHP(10);
    }
}
