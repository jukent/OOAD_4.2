package treasurehunt;

import util.DiceRolls;

public class QuickHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.

    protected static final int NEEDEDSCORE = 6;
    protected static final int THREESIDES = 3;
    protected static final int DICESIDES = 6;


    /**
     * Constructor for a Quick Treasure Hunt.
     */
    public QuickHuntBehavior() {
        setSearchType("Quick"); // String name
        setNeededScore(NEEDEDSCORE); // Required dice roll of 9
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrites the abstract treasure hunting with a Quick Hunt.
     */
    @Override
    public int searchTreasure() {
        if (DiceRolls.rollDice(THREESIDES) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(DICESIDES)
            + DiceRolls.rollDice(DICESIDES);
        }
    }
}
