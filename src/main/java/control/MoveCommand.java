package control;
import dungeon.*;
import entity.Character;
import game.*;
import movement.BlinkMovement;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Hashtable;

public class MoveCommand  extends Command {
    private Hashtable<Integer, String> movementOptions
        = new Hashtable<Integer, String>();
    private BlinkMovement portalMovement = new BlinkMovement();


    /**
     * @param characterRef Character
     * @param gameRef GameEngine
     *
     * Constructor for the Move Command.
     */
    MoveCommand(final Character characterRef, final GameEngine gameRef) {
        super(characterRef, gameRef);
    }


    /* (non-Javadoc)
     * @see control.Command#execute()
     *
     * Executes the Move Command.
     */
    @Override
    public void execute() {

        boolean chosen = false;
        Scanner input = this.gameRef.getPrinter().scanner;
        Room currentRoom = this.characterRef.getLocation();
        Room oldRoom = this.characterRef.getLocation();
        Room newRoom;

        while (!chosen) {
            movementOptions.clear();
            System.out.println();
            System.out.println("Movement Options:");
            ArrayList<String> exits = currentRoom.getExits();

            if (characterRef.checkPortalInInventory()) {
                System.out.println(String.valueOf(0)
                    + ": Portal (random room)");
                movementOptions.put(0, "Blink");
            }
            for (int i = 0; i < exits.size(); i++) {
                System.out.println(String.valueOf(i + 1)
                    + ": " + exits.get(i));
                movementOptions.put(i + 1, exits.get(i));
            }

            System.out.print("Select a direction: ");
            int choice = Integer.valueOf(input.nextLine());
            if (movementOptions.containsKey(choice)) {
                chosen = true;
                if (choice == 0) {
                    portalMovement.move(characterRef, gameRef.getDungeon());
                    newRoom = this.characterRef.getLocation();
                } else {
                    newRoom = gameRef.getDungeon()
                        .getRoom(movementOptions.get(choice));
                    characterRef.setLocation(newRoom);
                }
                gameRef.tracker.characterMoved(this.characterRef,
                    oldRoom, newRoom);
            } else {
                System.out.print("Try again...");
                chosen = false;
            }
        }
    }
}
