package movement;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Entity;

public class OrbitMovement extends MovementBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for Orbiter Movement.
     */
    public OrbitMovement() {
        setMovementType("Orbit");
    }


    /* (non-Javadoc)
     * @see movement.MovementBehavior#move(entity.Entity, dungeon.Dungeon)
     *
     * Replace abstract Entity movement with Orbiter movement.
     * Moves (clockwise or counterclockwise).
     */
    @Override
    public void move(final Entity entity, final Dungeon dungeon) {
        Room currentRoom = entity.getLocation();
        String direction = entity.getDirection();

        Integer level = currentRoom.getLevel();
        Integer row = currentRoom.getRow();
        Integer column = currentRoom.getColumn();

        if (direction.equals("clockwise")) {
            // Orbit Clockwise
            if (row == 0 && column == 0) {
                String nextRoomName = new String("(" + level + "-0-1)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 0 && column == 1) {
                String nextRoomName = new String("(" + level + "-0-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 0 && column == 2) {
                String nextRoomName = new String("(" + level + "-1-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 1 && column == 2) {
                String nextRoomName = new String("(" + level + "-2-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 2) {
                String nextRoomName = new String("(" + level + "-2-1)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 1) {
                String nextRoomName = new String("(" + level + "-2-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 0) {
                String nextRoomName = new String("(" + level + "-1-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 0) {
                String nextRoomName = new String("(" + level + "-1-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 1 && column == 0) {
                String nextRoomName = new String("(" + level + "-0-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            }
        } else {
            // Orbit Counterclockwise;
            if (row == 0 && column == 0) {
                String nextRoomName = new String("(" + level + "-1-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 1 && column == 0) {
                String nextRoomName = new String("(" + level + "-2-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 0) {
                String nextRoomName = new String("(" + level + "-2-1)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 1) {
                String nextRoomName = new String("(" + level + "-2-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 2 && column == 2) {
                String nextRoomName = new String("(" + level + "-1-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 1 && column == 2) {
                String nextRoomName = new String("(" + level + "-0-2)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 0 && column == 2) {
                String nextRoomName = new String("(" + level + "-0-1)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            } else if (row == 0 && column == 1) {
                String nextRoomName = new String("(" + level + "-0-0)");
                Room newRoom = dungeon.getRoom(nextRoomName);
                entity.setLocation(newRoom);
            }
        }
    }
}
