package entity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import movement.OrbitMovement;

public class Orbiter extends Creature {
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Constructor for Orbiters must be passed an ID integer 'id'
     * and the Dungeon.
     * Orbiters are assigned a starting room at construction.
     */
    public Orbiter(final int id, final Dungeon map) {
        setID(id); // Orbiter ID value
        setHP(1); // 1 Health point for Creatures
        setDungeon(map); // Game Dungeon
        setMovementBehavior(new OrbitMovement()); // MovementType is Orbit
        setName("Orbiter"); // String name
        setLocation(pickStartingRoom()); // Orbiters start in any outside Room
        setDirection(pickRandomDirection()); // Clockwise or Counterclockwise
        setType(EntityEnum.ORBITER);
    }


    /* (non-Javadoc)
     * @see entity.creature.Creature#setStartingRoom()
     *
     * Randomly generate starting room for Orbiters from
     * any exterior room on any level
     */
    @Override
    protected Room pickStartingRoom() {
        // Get map of possible rooms pointing to main dungeon Room objects.
        Hashtable<String, Room> possibleRoomMap = new Hashtable<String, Room>();
        possibleRoomMap.putAll(getDungeon().getMap());
        // Learned method from Geeks for Geeks:
        // "How to Copy Map Content to Another Hashtable in Java?
        // https://www.geeksforgeeks.org/
        //  how-to-copy-map-content-to-another-hashtable-in-java/
        possibleRoomMap.remove("(0-1-1)"); // remove entrace room
        possibleRoomMap.remove("(1-1-1)"); // remove 1st floor center room
        possibleRoomMap.remove("(2-1-1)"); // remove 2nd floor center room
        possibleRoomMap.remove("(3-1-1)"); // remove 3rd floor center room
        possibleRoomMap.remove("(4-1-1)"); // remove 4th floor center room

        // Randomly select one of the rooms -
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


    /**
     * @return String
     *
     * Randomly return "clockwise" or "counterclockwise"
     * to set Orbiter direction
     */
    private String pickRandomDirection() {
        ArrayList<String> directions = new ArrayList<String>();
        directions.add("clockwise");
        directions.add("counterclockwise");

        Random random = new Random();
        int i = random.nextInt(2);

        String direction = directions.get(i);
        return direction;
    }
}
