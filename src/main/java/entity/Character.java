package entity;

import java.util.ArrayList;

import movement.BlinkMovement;
import treasure.Treasure;
import treasurehunt.TreasureHuntBehavior;

public abstract class Character extends Entity {

    private TreasureHuntBehavior searchBehavior; // Treasure Hunting behavior
    private ArrayList<Treasure> inventory
        = new ArrayList<Treasure>(); // ArrayList of Treasure inventory
    private ArrayList<String> inventoryTypes
        = new ArrayList<String>(); // ArrayList of Treasure inventory types
        // (Strings)


    /**
     * Checks if the Character has a Portal in their Treasure inventory.
     * If so, sets their MovementBehavior to BlinkMovement (to use the Portal)
     * if not, Characters continue RandomWalk MovementBehavior.
     *
     * A more intelligent system could be used later to randomly let Characters
     * chose whether to Blink or not,
     * but this was not specified and is simpler to code -
     * so we are assuming that Characters love to use their portal gun.
     */
    public void checkPortalInInventory() {
        ArrayList<String> inventoryTypes =  getInventoryTypes();
        if (inventoryTypes.contains("Portal")) {
            BlinkMovement blinkMovement = new BlinkMovement();
            setMovementBehavior(blinkMovement);
        }
    }


    /**
     * @return int
     *
     * This abstract method calls a Character's treasure hunting behavior
     * and returns their "treasure roll" as an integer.
     */
    public int searchTreasure() {
        return searchBehavior.searchTreasure();
    }


    /**
     * @param n int
     *
     * This method adds health to a Character's health points.
     */
    public void addHealth(final int n) {
        setHP(getHP() + n);
    }


    /**
     * @return ArrayList<Treasure>
     *
     * This method exposes a Character's Treasure inventory.
     */
    public ArrayList<Treasure> getInventory() {
        return this.inventory;
    }


    /**
     * @param treasure Treasure
     *
     * This method adds a single Treasure to the Character's treasure inventory
     * and updates their Treasure inventory types to match.
     */
    public void addInventory(final Treasure treasure) {
        ArrayList<Treasure> inventory = getInventory();
        inventory.add(treasure);

        ArrayList<String> inventoryTypes = getInventoryTypes();
        inventoryTypes.add(treasure.getTreasureType());
        setInventoryTypes(inventoryTypes);
    }


    /**
     * @return ArrayList<String>
     *
     * This method exposes the types of Treasure in a Character's inventory.
     */
    public ArrayList<String> getInventoryTypes() {
        return inventoryTypes;
    }


    /**
     * @param inventoryTypes ArrayList<String>
     *
     * This method sets the Character's Treasure inventory types.
     */
    public void setInventoryTypes(final ArrayList<String> inventoryTypes) {
        this.inventoryTypes = inventoryTypes;
    }


    /**
     * @return String
     *
     * This method returns the Character's Treasure inventory types as a string.
     */
    public String getInventoryString() {
        ArrayList<String> inventoryArray =  getInventoryTypes();
        String inventoryString = String.join(", ", inventoryArray);
        return inventoryString;
    }


    /**
     * @return TreasureHunt
     *
     * Returns a Character's Treasure Hunt behavior.
     */
    public TreasureHuntBehavior getSearchBehavior() {
        return this.searchBehavior;
    }


    /**
     * @param searchBehavior TreasureHuntBehavior
     *
     * Sets the Character's Treasure Hunt behavior.
     */
    public void setSearchBehavior(final TreasureHuntBehavior searchBehavior) {
        this.searchBehavior = searchBehavior;
    }

    




}
