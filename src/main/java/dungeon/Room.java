package dungeon;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Creature;
import entity.Character;
import treasure.Treasure;

public class Room {

    private final int level; // The level coordinate for this room - Integer
    private final int row; // The row coordinate for this room - Integer
    private final int column; // The column coordinates for this room - Integer
    private String name; // The name  of this room, i.e. "(2-0-0)" - String
    private ArrayList<String> exits; // ArrayList of neighboring room names
    // i.e. ["(2-1-0)","(2-0-1)"]

    private ArrayList<Character> charactersInRoom; // ArrayList of Character
    private ArrayList<Creature> creaturesInRoom; // ArrayList of Creature
    private ArrayList<Treasure> treasuresInRoom; // ArrayList of Treasure


    // Avoiding magic numbers
    protected static final int MINLEVEL = 1; // Dungeon highest level
    // (not starting room)
    protected static final int MAXLEVEL = 4; // Dungeon max levels


    /**
     * @param level Integer
     * @param row Integer
     * @param column Integer
     *
     * Constructs the room object based on its coordinates.
     *
     * Rooms have level, row, and column coordinates,
     * as well as a name generated from these coordinates,
     * and a mapping of valid standard exit room names.
     */
    public Room(final int level, final int row, final int column) {
        // An example of identity
        // A lot of effort is made throughout the code to make sure identical
        // Room objects can be access from either the Dungeon object
        // or from subsets (seen in the new Hashtables in Creature movement)


        // constructor
        this.level = level;
        this.row = row;
        this.column = column;

        this.name = new String("(" + level + "-" + row + "-" + column + ")");

        ArrayList<String> exits = findExits();
        this.exits = exits;


        this.charactersInRoom = new ArrayList<Character>();
        this.creaturesInRoom = new ArrayList<Creature>();
        this.treasuresInRoom = new ArrayList<Treasure>();
    }


    /**
     * @return name: String
     *
     * Let other classes grab a room's name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return exits: ArrayList<String>
     * (cardinal directions, neighboring room names)
     *
     * Let other classess access a room's neighboring rooms
     * (and in what direction if we need that).
     */
    public ArrayList<String> getExits() {
        return exits;
    }


    /**
     * @return level: Integer
     *
     * Let other classess easily access a room's level.
     * Needed for creatures whose movement may be limited
     * to one level and for tests.
     */
    public Integer getLevel() {
        return level;
    }


    /**
     * @return row: Integer
     *
     * Let other classess easily access a room's row.
     */
    public Integer getRow() {
        return row;
    }


    /**
     * @return column: Integer
     *
     * Let other classess easily access a room's column.
     */
    public Integer getColumn() {
        return column;
    }


    /**
     * @return ArrayList<Characters>
     *
     * This method returns an ArrayList of the Characters in the Room.
     */
    public ArrayList<Character> getCharactersInRoom() {
        return this.charactersInRoom;
    }


    /**
     * @param charactersInRoom ArrayList<Characters>
     *
     * This method stores Room occupancy as an ArrayList
     * of Characters in the Room.
     *
     * This is updated by the Tracker as an example of the Observer pattern.
     */
    public void setCharactersInRoom(
        final ArrayList<Character> charactersInRoom) {
            this.charactersInRoom = charactersInRoom;
    }


    /**
     * @return ArrayList<Creatures>
     *
     * This method returns an ArrayList of the Creatures in the Room.
     */
    public ArrayList<Creature> getCreaturesInRoom() {
        return this.creaturesInRoom;
    }


    /**
     * @param creaturesInRoom ArrayList<Creatures>
     *
     * This method stores Room occupancy as an ArrayList
     * of Creatures in the Room.
     *
     * This is updated by the Tracker as an example of the Observer pattern.
     */
    public void setCreaturesInRoom(final ArrayList<Creature> creaturesInRoom) {
        this.creaturesInRoom = creaturesInRoom;
    }


    /**
     * @return ArrayList<Treasure>
     *
     * This method exposes the ArrayList of Treasures in the room.
     */
    public ArrayList<Treasure> getTreasuresInRoom() {
        return this.treasuresInRoom;
    }


    /**
     * @param treasuresInRoom ArrayList<Treasure>
     *
     * This method sets the Arraylist of Treasures in the room.
     *
     * This is updated by the Tracker as an example of the Observer pattern.
     */
    public void setTreasuresInRoom(final ArrayList<Treasure> treasuresInRoom) {
        this.treasuresInRoom = treasuresInRoom;
    }


    /**
     * @return HashMap<String, String> (direction, neighboring room name)
     *
     * This method finds the valid standard exits for each rooom based on its
     * level, row, and column coordinates.
     *
     * Since, we already know all valid rooom names, we can generate
     * exit connections when each rooom is generated without needing
     * to wait for an object containing all of the rooms to exist.
     */
    private HashMap<String, String> findExits() {
        HashMap<String, String> exits = new HashMap<String, String>();
        if (this.level == 0) {
            // If in starting room (0-1-1), only stairs down
            String neighboringRoom = new String("(1-1-1)");
            exits.put("Down", neighboringRoom);
        } else {
            // If already in the dungeon . . .
            if (this.row != 0) {
                // If not in north-most row of level, door to north
                Integer northRow = this.row - 1;
                String northRoom
                    = new String("(" + this.level + "-"
                    + northRow + "-" + this.column + ")");
                exits.put("North", northRoom);
            }
            if (this.row != 2) {
                // If not in south-most row of level, door to south
                Integer southRow = this.row + 1;
                String southRoom
                    = new String("(" + this.level + "-"
                    + southRow + "-" + this.column + ")");
                exits.put("South", southRoom);
            }
            if (this.column != 0) {
                // If not in west-most row of level, door to west
                Integer westColumn = this.column - 1;
                String westRoom
                    = new String("(" + this.level + "-"
                    + this.row + "-" + westColumn + ")");
                exits.put("West", westRoom);
            }
            if (this.column != 2) {
                // If not in east-most row of level, door to east
                Integer eastColumn = this.column + 1;
                String eastRoom
                    = new String("(" + this.level + "-"
                    + this.row + "-" + eastColumn + ")");
                exits.put("East", eastRoom);
            }
            if (this.column == 1 && this.row == 1) {
                // If in center room of level,
                // there is a staircase up and or down

                // Stairs up
                Integer upLevel = this.level - 1;
                String upRoom
                    = new String("(" + upLevel + "-"
                    + this.row + "-" + this.column + ")");
                if (this.level != MINLEVEL) {
                    exits.put("Up", upRoom);
                } else {
                    // Specify "Exit" if in (1-1-1)
                    exits.put("Exit", upRoom);
                }

                if (this.level != MAXLEVEL) {
                    // If not on bottom level, stairs down
                    // Currently bottom level is hard coded to be 4,
                    // hoping to change this
                    Integer downLevel = this.level + 1;
                    String downRoom
                        = new String("(" + downLevel + "-"
                        + this.row + "-" + this.column + ")");
                    exits.put("Down", downRoom);
                }
            }
        }
        return exits;
    }
}
