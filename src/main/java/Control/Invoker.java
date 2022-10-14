package control;
import entity.*;
import entity.Character;
import game.GameEngine;
import treasure.Treasure;
import dungeon.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Invoker {
    private Character characterRef;
    private GameEngine gameRef;
    private MoveCommand moveCommand;
    private FightCommand fightCommand;
    private SearchCommand searchCommand;
    private CelebrateCommand celebrateCommand;
    private Hashtable<Integer, Command> remote
        = new Hashtable<Integer, Command>();


    /**
     * @param characterRef Character
     * @param gameRef Game Engine
     *
     * Constructor for the Command Invoker.
     */
    public Invoker(final Character characterRef, final GameEngine gameRef) {
        this.characterRef = characterRef;
        this.gameRef = gameRef;
        moveCommand = new MoveCommand(characterRef, gameRef);
        fightCommand = new FightCommand(characterRef, gameRef);
        searchCommand = new SearchCommand(characterRef, gameRef);
        celebrateCommand = new CelebrateCommand(characterRef, gameRef);
    }

    /**
     * The control sequenc.
     */
    public void controlSequence() {
        int moveCount = 0;
        int exitChoice = 0;
        int playerChoice = 0;
        boolean roundCompleted = false;
        Scanner input = gameRef.getPrinter().scanner;
        remote.clear();
        System.out.println("New Turn");

        while (!roundCompleted) {
            int choiceNum = 1;
            int moveChoice = 0;
            Room currentRoom = this.characterRef.getLocation();
            ArrayList<Creature> creaturesInRoom
                = currentRoom.getCreaturesInRoom();
            ArrayList<Treasure> treasuresInRoom
                = currentRoom.getTreasuresInRoom();

            System.out.println();
            System.out.println(this.characterRef.getTitle() + " options: ");
            if (moveCount < this.characterRef.getMoveCount()) {
                System.out.println(String.valueOf(choiceNum) + ": Move");
                remote.put(choiceNum, moveCommand);
                moveChoice = choiceNum;
                choiceNum += 1;
            }
            if (treasuresInRoom.size() > 0) {
                System.out.println(String.valueOf(choiceNum)
                    + ": Search for Treasure");
                remote.put(choiceNum, searchCommand);
                choiceNum += 1;
            }
            if (creaturesInRoom.size() > 0) {
                System.out.println(String.valueOf(choiceNum)
                    + ": Fight Monsters");
                remote.put(choiceNum, fightCommand);
                choiceNum += 1;
            }
            System.out.println(String.valueOf(choiceNum) + ": PARTY!!!!");
            remote.put(choiceNum, celebrateCommand);
            choiceNum += 1;

            System.out.println(String.valueOf(choiceNum) + ": End Turn");
            exitChoice = choiceNum;
            choiceNum += 1;

            System.out.print("Your choice: ");
            String nextline = input.nextLine();
            playerChoice = Integer.valueOf(nextline);

            if (playerChoice == exitChoice) {
                roundCompleted = true;
                System.out.println("We ended the turn");
            } else if (remote.containsKey(playerChoice)) {
                remote.get(playerChoice).execute();
                if (playerChoice == moveChoice) {
                    moveCount += 1;
                    System.out.println("Move Count");
                    System.out.println(moveCount);
                }
            } else {
                System.out.println("Try again");
            }
        }
    }
}
