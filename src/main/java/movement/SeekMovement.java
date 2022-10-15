package movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Entity;
import entity.Character;

public class SeekMovement extends MovementBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for Seeker Movement.
     */
    public SeekMovement() {
        this.setMovementType("Seek");
    }



    /* (non-Javadoc)
     * @see movement.MovementBehavior#move(entity.Entity, dungeon.Dungeon)
     *
     * Seekers move by staying still and waiting for a Character to be in
     * a nearby Room, then they move to the room with the Character.
     */
    @Override
    public void move(final Entity entity, final Dungeon dungeon) {
        Room currentRoom = entity.getLocation();

        // List of nearby rooms
        Collection<String> exitsCol = currentRoom.getExits().values();
        ArrayList<String> exits = new ArrayList<String>(exitsCol);
        // Remove Starting Room so Creatures cannot exit the Dungeon
        exits.remove("(0-1-1)");

        // Populate an ArrayList of populated nearby rooms (with characters)
        ArrayList<Room> populatedExits = new ArrayList<>();
        for (String x: exits) {
            // Convert Exit Room-Name Strings to Rooms
            Room exitRoom = dungeon.getRoom(x);
            // Check if a Character is in the Exit Room
            ArrayList<Character> charactersInRoom
                = exitRoom.getCharactersInRoom();
            if (charactersInRoom.size() > 0) {
                // If character in room add it to possible exit_rooms
                populatedExits.add(exitRoom);
            }
        }

        // Move based on interesections
        if (populatedExits.size() == 0) {
            // If no intersection, don't move
            entity.setLocation(entity.getLocation());
        } else if (populatedExits.size() == 1) {
            // If one intersection, move there
            Room newRoom = populatedExits.get(0);
            entity.setLocation(newRoom);
        } else {
            // If multiple intersections, choose one randomly
            Random random = new Random();
            int i = random.nextInt(populatedExits.size());

            Room newRoom = populatedExits.get(i);
            entity.setLocation(newRoom);
        }
    }
}
