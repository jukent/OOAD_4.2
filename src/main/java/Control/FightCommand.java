package control;
import entity.*;
import dungeon.*;
import entity.Character;
import java.util.ArrayList;
import game.*;

public class FightCommand extends Command {


    /**
     * @param CharacterRef Character
     * @param gameRef GameEngine
     *
     * Constructor for the Fight Command.
     */
    FightCommand(final Character characterRef, final GameEngine gameRef) {
        super(characterRef, gameRef);

    }


    /* (non-Javadoc)
     * @see control.Command#execute()
     *
     * Executes the Fight Command.
     */
    @Override
    public void execute() {
        Room currentRoom = characterRef.getLocation();
        ArrayList<Creature> creaturesInRoom = currentRoom.getCreaturesInRoom();
        for (int j = 0; j < creaturesInRoom.size(); j++) {
            Creature creature = creaturesInRoom.get(j);
            gameRef.recieveFightCommand(this.characterRef, creature);
        }
    }

}
