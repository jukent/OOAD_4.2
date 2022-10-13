package Control;
import entity.*;

public class MoveCommand  extends Command{
    MoveCommand(Entity CharacterRef){
        super(CharacterRef);}


    @Override
    public void execute() {
        // TODO Auto-generated method stub
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
