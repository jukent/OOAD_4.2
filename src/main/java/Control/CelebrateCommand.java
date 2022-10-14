package Control;
import entity.Character;
import game.*;

public class CelebrateCommand extends Command{
    CelebrateCommand(Character CharacterRef, GameEngine gameRef){
        super( CharacterRef, gameRef);
    }

    @Override
    public void execute() {
        this.CharacterRef.getCelebrationBehavior().celebrate();
        System.out.println();
    }

}
