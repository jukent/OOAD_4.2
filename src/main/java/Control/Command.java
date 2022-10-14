package Control;
import entity.Character;
import game.GameEngine;

public abstract class Command {
    Character CharacterRef;
    GameEngine gameRef;
    //Constructor
    Command(Character CharacterRef, GameEngine gameRef){
        this.CharacterRef = CharacterRef;
        this.gameRef = gameRef;
    }

    public abstract void execute();
}
