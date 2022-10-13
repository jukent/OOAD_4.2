package Control;
import entity.*;

public abstract class Command {
    Entity CharacterRef;
    //Constructor
    Command(Entity CharacterRef){
        this.CharacterRef = CharacterRef;
    }
    
    public abstract void execute();
}
