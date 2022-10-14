package Control;
import dungeon.*;
import entity.Character;
import game.*;
import movement.BlinkMovement;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Hashtable;

public class MoveCommand  extends Command{
    private Hashtable<Integer,String> movementOptions = new Hashtable<Integer,String>(); 
    private BlinkMovement portalMovement = new BlinkMovement();
    
    MoveCommand(Character CharacterRef, GameEngine gameRef){
        super( CharacterRef, gameRef);}


    @Override
    public void execute() {
        
        boolean chosen = false;
        Scanner Input = new java.util.Scanner(System.in);
        Room currentRoom = this.CharacterRef.getLocation();
        Room oldRoom = this.CharacterRef.getLocation();
        Room newRoom;
        
        while(!chosen){
            movementOptions.clear();
            System.out.println("Movement Options:");
            ArrayList<String> exits = currentRoom.getExits();

            if(CharacterRef.checkPortalInInventory()){
                System.out.println(String.valueOf(0) + ": Portal (random room)");
                movementOptions.put(0, "Blink");
            }
            for(int i = 1; i <= exits.size(); i++){
                System.out.println(String.valueOf(i) + ": " + exits.get(i));
                movementOptions.put(i, exits.get(i));

            }


            System.out.print("Select a direction: ");
            int Choice = Integer.valueOf(Input.nextLine());
            if(movementOptions.contains(Choice)){
                chosen = true;
                if(Choice == 0){
                    portalMovement.move(CharacterRef, gameRef.getDungeon());
                    newRoom = this.CharacterRef.getLocation();
                }
                else{
                    newRoom = gameRef.getDungeon().getRoom(movementOptions.get(Choice));
                    CharacterRef.setLocation(newRoom);
                }
                gameRef.tracker.characterMoved(this.CharacterRef, oldRoom, newRoom);
            }
            else{
                System.out.print("Try again...");
                chosen = false;
            }
        }
        Input.close();
    }

}
