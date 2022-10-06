package treasurehunt;

import util.DiceRolls;

public class CarefulHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.

    protected static final int NEEDEDSCORE = 4;
    protected static final int DICESIDES = 6;


    /**
     * Constructor for a Careful Treasure Hunt.
     */
    public CarefulHuntBehavior() {
        setSearchType("Careful"); // String Name
        setNeededScore(NEEDEDSCORE); // Required dice roll of Integer 7
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrides the abstract treasure hunting with a Careful Hunt.
     */
    @Override
    public int searchTreasure() {
        return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
    }
}
