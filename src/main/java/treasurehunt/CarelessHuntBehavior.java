package treasurehunt;

import util.DiceRolls;

public class CarelessHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.

    protected static final int NEEDEDSCORE = 7;
    protected static final int DICESIDES = 6;


    /**
     * Constructor for a Careless Treasure Hunt.
     */
    public CarelessHuntBehavior() {
        setSearchType("Careless"); // String name
        setNeededScore(NEEDEDSCORE); // Required Dice Roll of 10
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrites the abstract treasure hunting with a Careless Hunt.
     */
    @Override
    public int searchTreasure() {
        return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
    }
}
