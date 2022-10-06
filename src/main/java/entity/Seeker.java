package entity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import movement.SeekMovement;

public class Seeker extends Creature {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Construct a Seeker with an Integer ID `id` and the Dungeon.
     */
    public Seeker(final int id, final Dungeon map) {
        setID(id); // Seeker ID value
        setHP(1); // 1 Health point for Creatures
        setDungeon(map); // Game Dungeon
        setMovementBehavior(new SeekMovement()); // MovementType is Seek
        setName("Seeker"); // String name
        setLocation(pickStartingRoom()); // Seekers start anywhere in Dungeon
    }



    /* (non-Javadoc)
     * @see entity.creature.Creature#setStartingRoom()
     *
     * Randomly generate starting room for Seekers
     * from any room in Dungeon, except "(0-1-1)"
     */
    @Override
    protected Room pickStartingRoom() {
        // Get new map of possible Rooms
        Hashtable<String, Room> possibleRoomMap = new Hashtable<String, Room>();
        possibleRoomMap.putAll(getDungeon().getMap());
        // Learned method from Geeks for Geeks:
        // "How to Copy Map Content to Another Hashtable in Java?
        // https://www.geeksforgeeks.org/
        //  how-to-copy-map-content-to-another-hashtable-in-java/
        possibleRoomMap.remove("(0-1-1)"); // Remove entrace room

        // Randomly select one of the Rooms -
        // learned from Stack Overflow question:
        // https://stackoverflow.com/questions/
        //  38248381/pick-a-random-element-from-a-hashtable
        ArrayList<Room> startingRooms
            = new ArrayList<Room>(possibleRoomMap.values());

        Random random = new Random();
        int i = random.nextInt(startingRooms.size());

        Room newRoom = startingRooms.get(i);

        // Start there
        return newRoom;
    }
}
