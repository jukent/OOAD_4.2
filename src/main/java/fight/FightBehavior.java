package fight;

public abstract class FightBehavior {

    // Implementation of the strategy OOP Design pattern. Subclasses change
    // behavior and encapsulate what varies.


    private String fightType; // String description of Fight Behavior


    /**
     * @return int
     *
     * This method executes the fight by rolling two dice
     * and adding any extra strength buffs.
     */
    public abstract int fight();


    /**
     * @return String
     *
     * Returns the type of FightBehavior
     * (i.e. "Creature", "Expert", "Stealthy", "Trained", "Untrained").
     */
    public String getFightType() {
        return this.fightType;
    }


    /**
     * @param fightType String
     *
     * Sets the FightBehavior type.
     */
    public final void setFightType(final String fightType) {
        this.fightType = fightType;
    }


    /**
     * Celebration decorator.
     */
    public void celebrate() { };
}
