package control;
import entity.Character;
import game.*;

public class SearchCommand extends Command {


    /**
     * @param characterRef Character
     * @param gameRef GameEngine
     *
     * Constructor for the Search Command.
     */
    SearchCommand(final Character characterRef, final GameEngine gameRef) {
        super( characterRef, gameRef);
    }


    /* (non-Javadoc)
     * @see control.Command#execute()
     *
     * Executes the Search Command.
     */
    @Override
    public void execute() {
        this.gameRef.recieveSearchCommand(this.characterRef);
    }
}
