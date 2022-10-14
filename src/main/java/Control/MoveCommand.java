package Control;
import entity.*;
import dungeon.*;
import entity.Character;
import game.*;

public class MoveCommand  extends Command{
    MoveCommand(Character CharacterRef, GameEngine gameRef){
        super( CharacterRef, gameRef);}


    @Override
    public void execute() {
        Room currentRoom = this.CharacterRef.getLocation();
        Room oldRoom = this.CharacterRef.getLocation();
        this.CharacterRef.checkPortalInInventory();
        this.CharacterRef.move();
        Room newRoom = this.CharacterRef.getLocation();
        gameRef.tracker.characterMoved(this.CharacterRef, oldRoom, newRoom);
    }

}
