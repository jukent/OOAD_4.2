package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class DanceCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight FightBehavior
     *
     * Dance Celebration constructor.
     */
    public DanceCelebration(final FightBehavior fight) {
        setFightRef(fight);
    }


     /**
     * @param celebrateRef Celebration
     *
     * Dance Celebration constructor.
     */
    public DanceCelebration(final Celebration celebrateRef) {
        setFightRef(celebrateRef.getFightRef());
        setCelebrationRef(celebrateRef);
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     *
     * Implements the "fight" dice-roll integer.
     */
    @Override
    public int fight() {
        return this.getFightRef().fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     *
     * This implementation executes dancing celebration
     * a random number of times.
     */
    @Override
    public void celebrate() {
        if (this.getCelebrationRef() != null) {
            this.getCelebrationRef().celebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(ROLLNUMBER) - 1; i++) {
            System.out.print("Dance! ");
        }
    }

    public String trackCelebrate() {
        String results = "";
        if (getCelebrationRef() != null) {
            results += getCelebrationRef().trackCelebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(3) - 1;  i++) {
            results += "Dance! ";
        }
        return results;
    }
}
