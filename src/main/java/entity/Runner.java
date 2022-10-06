package entity;

import dungeon.Dungeon;
import fight.UntrainedFightBehavior;
import treasurehunt.QuickHuntBehavior;

public class Runner extends Character {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Constructs a runner with an Integer `id` and the Dungeon.
     */
    public Runner(final int id, final Dungeon map) {
        setID(id); // Runner ID value
        setDungeon(map); // Game Dungeon
        setLocation(getDungeon().getRoom("(0-1-1)")); // Begin in Entrance Room
        setMoveCount(2); // Int moveCount of 2, Runner's get twice the turns,
        setFightBehavior(new UntrainedFightBehavior()); // FightType is
        // Untrained
        setSearchBehavior(new QuickHuntBehavior()); // SearchType is Quick
        setName("Runner"); // String name
    }
}
