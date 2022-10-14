package Control;
import entity.*;
import entity.Character;
import game.GameEngine;
import treasure.Treasure;
import dungeon.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Invoker {
    private Character CharacterRef;
    private MoveCommand Move_Command;
    private FightCommand Fight_Command;
    private SearchCommand Search_Command;
    private CelebrateCommand Celebrate_Command;
    private Hashtable<Integer, Command> Remote;

    public Invoker(Character CharacterRef,GameEngine gameRef){
        this.CharacterRef = CharacterRef;
        Move_Command = new MoveCommand(CharacterRef,gameRef);
        Fight_Command = new FightCommand(CharacterRef,gameRef);
        Search_Command = new SearchCommand(CharacterRef,gameRef);
        Celebrate_Command = new CelebrateCommand(CharacterRef,gameRef);
    }

    public void ControlSequence(){
        int MoveCount = 0;
        int ExitChoice = 0;
        int PlayerChoice = 0;
        boolean RoundCompleted = false;
        Scanner Input = new java.util.Scanner(System.in); 
        Remote.clear();

        while(!RoundCompleted){
            int ChoiceNum = 1;
            int MoveChoice = 0;
            Room currentRoom = this.CharacterRef.getLocation();
            ArrayList<Creature> creaturesInRoom = currentRoom.getCreaturesInRoom();
            ArrayList<Treasure> treasuresInRoom = currentRoom.getTreasuresInRoom();
    
            System.out.println(this.CharacterRef.getTitle()+" options: ");
            if(MoveCount == this.CharacterRef.getMoveCount()){
                System.out.println(String.valueOf(ChoiceNum) + ": Move");
                Remote.put(ChoiceNum,Move_Command);
                MoveChoice = ChoiceNum;
                ChoiceNum += 1;
            }
            if(treasuresInRoom.size() > 0){
                System.out.println(String.valueOf(ChoiceNum) + ": Search for Treasure");
                Remote.put(ChoiceNum,Search_Command);
                ChoiceNum += 1;
            }
            if(creaturesInRoom.size() > 0){
                System.out.println(String.valueOf(ChoiceNum) + ": Fight Monsters");
                Remote.put(ChoiceNum,Fight_Command);
                ChoiceNum += 1;
            }
            System.out.println(String.valueOf(ChoiceNum) + ": PARTY!!!!");
            Remote.put(ChoiceNum,Celebrate_Command);
            ChoiceNum += 1;
    
            System.out.println(String.valueOf(ChoiceNum) + ": End Turn");
            RoundCompleted = true;
            ExitChoice = ChoiceNum;
            ChoiceNum += 1;

            System.out.print("Your choice: ");
            PlayerChoice = Integer.valueOf(Input.nextLine());




            if(PlayerChoice == ExitChoice){
                RoundCompleted = true;
            }
            else if (Remote.containsKey(PlayerChoice)){
                Remote.get(PlayerChoice).execute();
                if(ChoiceNum==MoveChoice){MoveCount += 1;}
            }
            else{
                System.out.println("Try again");
            }
        

        }
        Input.close();


        
    }
}
