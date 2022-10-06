package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class ShoutCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight FightBehavior
     *
     * Shout Celebration constructor.
     */
    public ShoutCelebration(final FightBehavior fight) {
        setFightRef(fight);
    }


     /**
     * @param celebrateRef Celebration
     *
     * Shout Celebration constructor.
     */
    public ShoutCelebration(final Celebration celebrateRef) {
        setFightRef(celebrateRef.getFightRef());
        setCelebrationRef(celebrateRef);
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     *
     * Returns the "fight" dice-roll integer.
     */
    public int fight() {
        return this.getFightRef().fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     *
     * Executes shouting celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if (this.getCelebrationRef() != null) {
            this.getCelebrationRef().celebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(ROLLNUMBER) - 1; i++) {
            System.out.print("Shout! ");
        }
    }

    public String trackCelebrate() {
        String results = "";
        if (getCelebrationRef() != null) {
            results += getCelebrationRef().trackCelebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            results += "Shout! ";
        }
        return results;
    }
}
