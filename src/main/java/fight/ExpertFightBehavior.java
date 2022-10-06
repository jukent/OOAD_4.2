package fight;

import util.DiceRolls;

public class ExpertFightBehavior extends FightBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern

    protected static final int DICESIDES = 6;



    /**
     * Constructor for an Expert Fighter.
     */
    public ExpertFightBehavior() {
        setFightType("Expert");
    }


    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     *
     * @return int
     *
     * Returns the dice roll for an Expert Fighter (strength buff of 2).
     */
    public int fight() {
        return DiceRolls.rollDice(DICESIDES)
            + DiceRolls.rollDice(DICESIDES) + 2;
    }
}
