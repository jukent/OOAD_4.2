package control;
import entity.Character;
import game.GameEngine;

public abstract class Command {
    Character characterRef;
    GameEngine gameRef;


    /**
     * @param characterRef Character
     * @param gameRef GameEngine
     *
     * Constructor for a Command.
     * This is the beggining of the Command Pattern
     */
    Command(final Character characterRef, final GameEngine gameRef) {
        this.characterRef = characterRef;
        this.gameRef = gameRef;
    }


    /**
     * Abstract Command execution mehtod.
     */
    public abstract void execute();
}
