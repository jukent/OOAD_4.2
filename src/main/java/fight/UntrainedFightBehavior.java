package fight;

import util.DiceRolls;

public class UntrainedFightBehavior extends FightBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    protected static final int DICESIDES = 6;


    /**
     * Constructor for an Untrained Fighter.
     */
    public UntrainedFightBehavior() {
        setFightType("Untrained");
    }


    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     *
     * @return int
     *
     * Returns the dice roll for an Untrained Fighter (no strength buff).
     */
    public int fight() {
        return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
    }
}
