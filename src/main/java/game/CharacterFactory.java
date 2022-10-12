package game;
import java.util.ArrayList;
import entity.*;
import entity.Character;
import dungeon.*;

public class CharacterFactory extends Factory{
    
    CharacterFactory(Dungeon dungeonRef){
        this.dungeonRef = dungeonRef;
    }

    public Character CreateEntity(EntityEnum type, String Title){
        switch(type){
            case RUNNER:
                return new Runner(this.ID, this.dungeonRef,Title);
            case BRAWLER:
                return new Brawler(this.ID, this.dungeonRef,Title);
            case THIEF:
                return new Thief(this.ID, this.dungeonRef,Title);
            case SNEAKER:
                return new Sneaker(this.ID, this.dungeonRef,Title);
            case NULL:
                return new Sneaker(this.ID, this.dungeonRef,Title);
            case BLINKER:
                return new Sneaker(this.ID, this.dungeonRef,Title);  
            case SEEKER:
                return new Sneaker(this.ID, this.dungeonRef,Title);
            case ORBITER:
                return new Sneaker(this.ID, this.dungeonRef,Title);
            default:
                return new Sneaker(this.ID, this.dungeonRef,Title);
    
        }




    }


    public ArrayList<Character> EntitySet(int copies){
        ArrayList<Character> characterList = new ArrayList<Character>();
        for(int i = 0; i<copies; i++){
        characterList.add(new Runner(this.ID, this.dungeonRef,""));
        this.ID++;
        characterList.add(new Sneaker(this.ID, this.dungeonRef,""));
        this.ID++;
        characterList.add(new Thief(this.ID, this.dungeonRef,""));
        this.ID++;
        characterList.add(new Brawler(this.ID, this.dungeonRef,""));
        this.ID++;}
        return characterList;
    }
    

}
