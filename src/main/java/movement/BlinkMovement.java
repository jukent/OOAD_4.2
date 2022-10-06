package movement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Entity;

public class BlinkMovement extends MovementBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for Blinker Movement.
     */
    public BlinkMovement() {
        setMovementType("Blink");
    }


    /* (non-Javadoc)
     * @see movement.MovementBehavior#move(entity.Entity, dungeon.Dungeon)
     *
     * Replace abstract movement with Blinker movement
     */
    @Override
    public void move(final Entity entity, final Dungeon dungeon) {
        Room currentRoom = entity.getLocation();

        // Get a new hashtable to store possible rooms called
        // Hashtable values point to a subset of the Room objects
        Hashtable<String, Room> possibleRoomMap = new Hashtable<String, Room>();
        possibleRoomMap.putAll(dungeon.getMap());
        // Learned method from Geeks for Geeks:
        //  "How to Copy Map Content to Another Hashtable in Java?
        // https://www.geeksforgeeks.org/
        //  how-to-copy-map-content-to-another-hashtable-in-java/
        possibleRoomMap.remove("0-1-1"); // Remove entrace room
        possibleRoomMap.remove(currentRoom.getName()); // Remove current room

        // Randomly select one of the rooms -
        // learned from Stack Overflow question:
        // https://stackoverflow.com/questions/
        //  38248381/pick-a-random-element-from-a-hashtable)
        // Convert values to an ArrayList of Room
        // so that Rooms can grabbed via index
        ArrayList<Room> possibleRooms
            = new ArrayList<Room>(possibleRoomMap.values());

        // Get random index from 0 (inclusive) to
        // the length of possible rooms (exclusive)
        Random random = new Random();
        int i = random.nextInt(possibleRooms.size());

        // Grab a Room from the random index
        Room newRoom = possibleRooms.get(i);

        // Move there
        entity.setLocation(newRoom);
    }
}
