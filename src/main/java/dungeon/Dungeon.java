package dungeon;

import java.util.Hashtable;

public class Dungeon {

    protected static final int NUMLEVELS = 4; // The number of levels
    private Hashtable<String, Room> map; // The map of this dungeon -
    // Hashtable of room names mapped to Rooms
    // Also example of abstraction -
    // Some methods are made private to prevent users
    // from accessing information that is unnecassary. This class contains
    // only the needed information to access it from the outside.
    // Any additional information is hidden in private methods.

    // Create a single object for eager Singleton pattern
    private static Dungeon instance = new Dungeon();

    /**
     * Constructs the Dungeon object which contains a map of all rooms
     *
     * Possibility to set NUM_LEVELS to an input parameter
     * so that this can be more easily extended for different Dungeon sizes.
     * Currently hardcoded to 4 for simplicity.
     */
    private Dungeon() {
        setMap(generateMap(NUMLEVELS));
    }


    /**
     * @return Dungeon
     *
     * Gets the only Dungeon.
     * Eager Singleton.
     */
    public static Dungeon getInstance() {
        return instance;
    }


    /**
     * @return map: Hashtable<String, Room> (room name, Room)
     *
     * Expose the map to other Classes
     */
    public Hashtable<String, Room> getMap() {
        return map;
    }


    /**
     * @param map Hashtable<String, Room>
     *
     * Sets the Dungeon map
     */
    public void setMap(final Hashtable<String, Room> map) {
        this.map = map;
    }


    /**
     * @param roomName String
     * @return room: Room
     *
     * Let other classes get a Room object based on its name.
     */
    public Room getRoom(final String roomName) {
        Room room = getMap().get(roomName);
        return room;
    }


    /**
     * @param  numLevels Integer
     * @return map: Hashtable<String, Room> (room names, room object)
     *
     * Generates the Rooms for the dungeon for any number of levels
     * ('numLevels'), with 3 rooms per level after the first level
     * (which has only one room).
     *
     * The Rooms are stored in a Hashtable map with room names as keys
     * pointing to Room objects as values.
     */
    private Hashtable<String, Room> generateMap(final int numLevels) {
        Hashtable<String, Room> map = new Hashtable<String, Room>();

        for (int l = 0; l <= numLevels; l++) {
            if (l == 0) {
                // On level 0, there is only one room
                Room room = new Room(l, 1, 1);
                map.put(room.getName(), room);
            } else {
                // On all other levels, there are 3x3 rooms
                for (int r = 0; r <= 2; r++) { // row
                    for (int c = 0; c <= 2; c++) { // column
                        Room room = new Room(l, r, c);
                        map.put(room.getName(), room);
                    }
                }
            }
        }
        return map;
    }
}
