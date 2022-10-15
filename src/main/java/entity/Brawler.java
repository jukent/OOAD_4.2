package entity;

import dungeon.Dungeon;
import fight.ExpertFightBehavior;
import treasurehunt.CarelessHuntBehavior;

public class Brawler extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     * @param name String
     *
     * Brawler constructor must be passed in an integer ID `id, the Dungeon,
     * and a name.
     * Brawler is constructed with starting room.
     */
    public Brawler(final int id, final Dungeon map, final String name) {
        setID(id); // Brawler ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Begin in Entrance Room
        setFightBehavior(new ExpertFightBehavior()); // FightType is Expert
        setSearchBehavior(new CarelessHuntBehavior());
        setHP(12);
        // SearchType is Careless
        setName(name); // String name
        setTitle("Brawler");
        setType(EntityEnum.BRAWLER);
    }
}
