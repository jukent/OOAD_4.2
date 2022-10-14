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
    private GameEngine gameRef;
    private MoveCommand Move_Command;
    private FightCommand Fight_Command;
    private SearchCommand Search_Command;
    private CelebrateCommand Celebrate_Command;
    private Hashtable<Integer, Command> Remote = new Hashtable<Integer, Command>();

    public Invoker(Character CharacterRef,GameEngine gameRef){
        this.CharacterRef = CharacterRef;
        this.gameRef = gameRef;
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
        Scanner Input = gameRef.getPrinter().scanner;
        Remote.clear();
        System.out.println("New Turn");


        while(!RoundCompleted){
            int ChoiceNum = 1;
            int MoveChoice = 0;
            Room currentRoom = this.CharacterRef.getLocation();
            ArrayList<Creature> creaturesInRoom = currentRoom.getCreaturesInRoom();
            ArrayList<Treasure> treasuresInRoom = currentRoom.getTreasuresInRoom();
    
            System.out.println();
            System.out.println(this.CharacterRef.getTitle()+" options: ");
            if(MoveCount < this.CharacterRef.getMoveCount()){
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
            ExitChoice = ChoiceNum;
            ChoiceNum += 1;

            System.out.print("Your choice: ");
            String nextline = Input.nextLine();
            PlayerChoice = Integer.valueOf(nextline);
            



            if(PlayerChoice == ExitChoice){
                RoundCompleted = true;
                System.out.println("We ended the turn");
            }
            else if (Remote.containsKey(PlayerChoice)){
                Remote.get(PlayerChoice).execute();
                if(PlayerChoice==MoveChoice){
                    MoveCount += 1;
                    System.out.println("Move Count");
                    System.out.println(MoveCount);
                }
            }
            else{
                System.out.println("Try again");
            }
            
        }
    }
}
