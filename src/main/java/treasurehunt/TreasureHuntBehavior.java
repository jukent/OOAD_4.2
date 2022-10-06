package treasurehunt;

public abstract class TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. Subclasses extend
    // this behavior.

    private String searchType; // String type for Treasue Hunting Behavior
    private int neededScore; // Required Integer dice roll to find treasure


    /**
     * @return int
     *
     * This abstract method searches for treasure by rolling dice
     * and returns the "dice roll" integer.
     */
    public abstract int searchTreasure();


    /**
     * @return int
     *
     * Returns the needed dice roll for a Treasure Search behavior.
     */
    public int getNeededScore() {
        return this.neededScore;
    }


    /**
     * @param n
     *
     * Sets the needed dice roll for a Treasure Search behavior.
     */
    public void setNeededScore(final int n) {
        this.neededScore = n;
    }


    /**
     * @return String
     *
     * Returns the String Treasure Hunt type for a Hunt behavior.
     */
    public String getSearchType() {
        return this.searchType;
    }


    /**
     * @param type String
     *
     * Sets the String Treasure Hunt type for a Hunt behavior.
     */
    public void setSearchType(final String type) {
        this.searchType = type;
    }

}
