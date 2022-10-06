package celebration;

import fight.FightBehavior;

/**
 * Abstract class for celebration decorators of fight behaviors.
 */
public abstract class Celebration extends FightBehavior {

    private Celebration celebrationRef; // Celebration reference
    private FightBehavior fightRef; // Fight reference

    protected static final int ROLLNUMBER = 3; // Roll number (not magic number)

    // This is our decorator pattern.
    // The celebration decorator decorates the fight behaviors
    // and can inherit fight references and celebration references
    // to modify the behavior of fight behavior using the same methods.


    /* (non-Javadoc)
     * @see fight.FightBehavior#fight()
     * @return int
     *
     * Abstract method returning a "fight" dice-roll integer.
     */
    public abstract int fight(); // Abstract fight roll


    /* (non-Javadoc)
     * @see fight.FightBehavior#celebrate()
     *
     * Abstract celebration method.
     */
    public abstract void celebrate();


    /**
     * Abstract celebration tracking method.
     */
    public abstract String trackCelebrate();


    /**
     * @return Celebration
     *
     * Returns the Celebration reference.
     */
    public Celebration getCelebrationRef() {
        return celebrationRef;
    }


    /**
     * @param celebrationRef Celebration
     *
     * Sets the Celebration reference.
     */
    final void setCelebrationRef(final Celebration celebrationRef) {
        this.celebrationRef = celebrationRef;
    }



    /**
     * @return FightBehavior
     *
     * Returns the decorated Fight Behavior.
     */
    public FightBehavior getFightRef() {
        return fightRef;
    }


    /**
     * @param fightRef FightBehavior
     *
     * Sets the decorated Fight Behavior.
     */
    final void setFightRef(final FightBehavior fightRef) {
        this.fightRef = fightRef;
    }
}
