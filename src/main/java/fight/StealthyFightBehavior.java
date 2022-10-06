package fight;

import util.DiceRolls;

public class StealthyFightBehavior extends FightBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    protected static final int COINFLIP = 2;
    protected static final int DICESIDES = 6;


    /**
     * Constructor for a Stealthy Fighter.
     */
    public StealthyFightBehavior() {
        setFightType("Stealth");
    }


    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     *
     * @return int
     *
     * Returns the dice roll for a Stealthy Fighter (strength buff of 1).
     * Stealthy fighters have a  50% chance of avoiding a fight,
     * indicated by a return value of -1.
     */
    public int fight() {
        if (DiceRolls.rollDice(COINFLIP) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(DICESIDES)
                + DiceRolls.rollDice(DICESIDES) + 1;
        }
    }
}
