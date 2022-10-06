package movement;

import dungeon.Dungeon;
import entity.Entity;

public abstract class MovementBehavior {

    // Implementation of the strategy OOP Design pattern.

    private String movementType;


    /**
     * Movement Constructor.
     */
    public MovementBehavior() { }


    /**
     * @return String
     *
     * Returns the MovementBehavior type
     * (e.g. "Blink", "Orbit", "RandomWalk", "Seek").
     */
    public String getMovementType() {
        return this.movementType;
    }


    /**
     * @param movementType String
     *
     * Sets the MovementBehavior type.
     */
    public void setMovementType(final String movementType) {
        this.movementType = movementType;
    }


    /**
     * @param entity Entity
     * @param dungeon Dungeon
     *
     * Abstract method for Entity movement.
     */
    public abstract void move(Entity entity, Dungeon dungeon);
}
