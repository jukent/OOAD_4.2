package Control;
import entity.*;
import treasure.Treasure;
import dungeon.*;
import java.util.ArrayList;

public class Invoker {
    Entity CharacterRef;
    MoveCommand Move_Command;
    FightCommand Fight_Command;
    SearchCommand Search_Command;
    CelebrateCommand Celebrate_Command;

    Invoker(Entity CharacterRef){
        this.CharacterRef = CharacterRef;
        Move_Command = new MoveCommand(CharacterRef);
        Fight_Command = new FightCommand(CharacterRef);
        Search_Command = new SearchCommand(CharacterRef);
        Celebrate_Command = new CelebrateCommand(CharacterRef);
    }

    public void ControlSequence(){
        int MoveCount = 0;
        boolean RoundCompleted = false;

        
        int ChoiceNum = 1;
        Room currentRoom = this.CharacterRef.getLocation();
        ArrayList<Creature> creaturesInRoom = currentRoom.getCreaturesInRoom();
        ArrayList<Treasure> treasuresInRoom = currentRoom.getTreasuresInRoom();

        System.out.println(this.CharacterRef.getTitle()+" options: ");
        if(MoveCount == this.CharacterRef.getMoveCount()){
            System.out.println(String.valueOf(ChoiceNum) + ": Move");
            ChoiceNum += 1;
        }
        if(treasuresInRoom.size() > 0){
            System.out.println(String.valueOf(ChoiceNum) + ": Search for Treasure");
            ChoiceNum += 1;
        }
        if(creaturesInRoom.size() > 0){
            System.out.println(String.valueOf(ChoiceNum) + ": Fight Monsters");
            ChoiceNum += 1;
        }
        System.out.println(String.valueOf(ChoiceNum) + ": PARTY!!!!");
        ChoiceNum += 1;
        System.out.println(String.valueOf(ChoiceNum) + ": End Turn");
        ChoiceNum += 1;


            Room oldRoom = character.getLocation();
            character.checkPortalInInventory();
            character.move();
            currentRoom = character.getLocation();
            Room newRoom = character.getLocation();
            tracker.characterMoved(character, oldRoom, newRoom);
            if (creaturesInRoom.size() > 0) {
                // If there are Creatures in the room, fight
                for (int j = 0; j < creaturesInRoom.size(); j++) {
                    Creature creature = creaturesInRoom.get(j);
                    simulateFight(character, creature);
                }
                continue;
            } else {
                // If there are no Creatures in the room, look for treasure
                simulateTreasureHunt(character);
            }
        }


        
    }


}
