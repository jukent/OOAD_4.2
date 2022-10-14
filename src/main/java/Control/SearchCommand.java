package Control;
import entity.Character;
import game.*;

public class SearchCommand extends Command{
    SearchCommand(Character CharacterRef, GameEngine gameRef){
        super( CharacterRef, gameRef);}

    @Override
    public void execute() {
        this.gameRef.recieveSearchCommand(this.CharacterRef);
    }
    }
