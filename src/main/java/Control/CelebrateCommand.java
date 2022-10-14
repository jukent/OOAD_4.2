package control;
import entity.Character;
import game.*;

public class CelebrateCommand extends Command {


    /**
     * @param characterRef Character
     * @param gameRef GameEngine
     *
     * Constructor for the Celebration Command.
     */
    CelebrateCommand(final Character characterRef, final GameEngine gameRef) {
        super(characterRef, gameRef);
    }


    /* (non-Javadoc)
     * @see control.Command#execute()
     *
     * Executes the Celebration Command.
     */
    @Override
    public void execute() {
        this.characterRef.getCelebrationBehavior().celebrate();
        System.out.println();
    }
}
