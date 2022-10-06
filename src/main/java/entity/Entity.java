package entity;

import dungeon.Dungeon;
import dungeon.Room;
import fight.CreatureFightBehavior;
import fight.FightBehavior;
import movement.MovementBehavior;
import movement.RandomWalkMovement;

public class Entity {
    // Example of abstraction -
    // Characters and Creatures all have their information grouped together
    // This was planned in the initial UML design, and was made abstract
    // because it allows a user to interact with characters without
    // knowing the full details.
    // Entity super class added later for movement behavior

    private int id = 0; // Integer ID value
    private Dungeon dungeon; // Game Dungeon
    private Room location; // Room location

    private String name
        = new String("Entity"); // Simple Entity name String
    private String direction
        = new String("clockwise"); // Dummy String direction value
    private static final  int HP = 3; // Health Points Integer
    private int hp = HP;
    private int moveCount = 1; // Integer movement count

    private FightBehavior fightBehavior
        = new CreatureFightBehavior(); // FightBehavior
    private MovementBehavior movementBehavior
        = new RandomWalkMovement(); // MovementBehavior


    /**
     * @return int
     *
     * This abstract method calls an Entity's fight behavior
     * and returns their "fight roll" as an integer.
     */
    public int fight() {
        return fightBehavior.fight();
    }


    /**
     * Template function for specific move directions
     * to be overwritten by more specific behavior.
     */
    public void move() {
        movementBehavior.move(this, dungeon);
    }


    /**
     * @param room Room
     *
     * This method manually sets Entity's location.
     */
    public void setLocation(final Room room) {
        this.location = room;
    }


    /**
     * @return Room
     *
     * This method returns an Entity's location.
     */
    public Room getLocation() {
        return this.location;
    }


    /**
     * @param direction String
     *
     * This method sets an Entity's "direction"
     *
     * This is only used for Orbiter's, but since we wanted to encapsalate
     * MovementBehavior to work for Creatures or Entity's
     * (so that Characters could blink), all Entity's needed to have any
     * parameters that could be called up by any of the 'move()' methods.
     */
    public void setDirection(final String direction) {
        this.direction = direction;
    }


    /**
     * @return String
     *
     * Make's an entity's movement direction accessible.
     */
    public String getDirection() {
        return this.direction;
    }


    /**
     * @return int
     *
     * This method returns an Entity's health.
     */
    public int getHealth() {
        return this.hp;
    }


    /**
     * @param n int
     *
     * This method decreases an Entity's health by the integer 'n'.
     */
    public void loseHealth(final int n) {
        this.hp -= n;
    }


    /**
     * @return String
     *
     * This method returns an Entity's name.
     */
    public String getName() {
        return this.name;
    }


    /**
     * @return String
     *
     * This method returns an Entity's type of FightBehavior
     * (i.e. "Creature", "Expert", "Stealthy", "Trained", "Untrained").
     */
    public String getFightType() {
        return this.fightBehavior.getFightType();
    }


    /**
     * @param fightBehavior FightBehavior
     *
     * This method sets an Entity's fight behavior.
     */
    public void setFightBehavior(final FightBehavior fightBehavior) {
        this.fightBehavior = fightBehavior;
    }


    /**
     * @return FightBehavior
     *
     * Returns an Entity's FightBehavior.
     */
    public FightBehavior getFightBehavior() {
        return this.fightBehavior;
    }


    /**
     * @return String
     *
     * This method returns an Entity's MovementType
     * (i.e., "Orbit", "RandomWalk", "Seek", "Blink").
     */
    public String getMovementType() {
        return this.movementBehavior.getMovementType();
    }


    /**
     * @param movementBehavior
     *
     * This method sets an Entity's MovementBehavior.
     */
    public void setMovementBehavior(final MovementBehavior movementBehavior) {
        this.movementBehavior = movementBehavior;
    }


    /**
     * @return int
     *
     * Returns an Entity's health points.
     */
    public int getHP() {
        return this.hp;
    }


    /**
     * @param hp int
     *
     * Sets an Entity's health points.
     */
    public void setHP(final int hp) {
        this.hp = hp;
    }


    /**
     * @return int
     *
     * Returns an Entity's ID.
     */
    public int getID() {
        return this.id;
    }


    /**
     * @param id int
     *
     * Sets an Entity's ID.
     */
    public void setID(final int id) {
        this.id = id;
    }


        /**
     * @return int
     *
     * Returns an Entity's move count.
     */
    public int getMoveCount() {
        return moveCount;
    }


    /**
     * @param moveCount
     *
     * Sets an Entity's move count.
     */
    public void setMoveCount(final int moveCount) {
        this.moveCount = moveCount;
    }


    /**
     * @return Dungeon
     *
     * Returns the Entity dungeon.
     */
    public Dungeon getDungeon() {
        return this.dungeon;
    }


    /**
     * @param dungeon Dungeon
     *
     * Sets the Entity dungeon.
     */
    public void setDungeon(final Dungeon dungeon) {
        this.dungeon = dungeon;
    }


    /**
     * @param name String
     *
     * Sets an Entity's name.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
