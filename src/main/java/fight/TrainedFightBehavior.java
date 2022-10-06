package fight;

import util.DiceRolls;

public class TrainedFightBehavior extends FightBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    protected static final int DICESIDES = 6;


    /**
     * Constructor for a Trained Fighter.
     */
    public TrainedFightBehavior() {
        setFightType("Trained");
    }

    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     *
     * @return int
     *
     * Returns the dice roll for a Trained Fighter (strength buff of 1).
     */
    public int fight() {
        return DiceRolls.rollDice(DICESIDES)
            + DiceRolls.rollDice(DICESIDES) + 1;
    }
}
