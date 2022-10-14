package Control;
import entity.*;
import dungeon.*;
import entity.Character;
import java.util.ArrayList;
import game.*;

public class FightCommand extends Command{

    FightCommand(Character CharacterRef, GameEngine gameRef){
        super( CharacterRef, gameRef);

    }
    
    @Override
    public void execute() {
        Room currentRoom = CharacterRef.getLocation();
        ArrayList<Creature> creaturesInRoom = currentRoom.getCreaturesInRoom();
        for (int j = 0; j < creaturesInRoom.size(); j++) {
            Creature creature = creaturesInRoom.get(j);
            gameRef.recieveFightCommand(this.CharacterRef, creature);
        }
    }

}
