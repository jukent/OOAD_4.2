package entity;

import dungeon.Dungeon;
import fight.TrainedFightBehavior;
import treasurehunt.CarefulHuntBehavior;

public class Thief extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Construct Thieves with an Integer ID `id` and the Dungeon
     */
    public Thief(final int id, final Dungeon map, final String Name) {
        setID(id); // Thief ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Start in Entrance Room
        setFightBehavior(new TrainedFightBehavior()); // FightType is Trained
        setSearchBehavior(new CarefulHuntBehavior()); // SearhType is Careful
        setName("Thief"); // String name
        setTitle(Name);
        setType(EntityEnum.THIEF);
        


    }
}
