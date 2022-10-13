package game;
import java.util.ArrayList;
import entity.*;
import entity.Character;
import dungeon.*;

public class CharacterFactory extends Factory{


    /**
     * @param dungeonRef Dungeon
     *
     * Constructs the Character factory.
     */
    CharacterFactory(final Dungeon dungeonRef) {
        this.dungeonRef = dungeonRef;
    }


    /* (non-Javadoc)
     * @see game.Factory#createEntity(entity.EntityEnum, java.lang.String)
     */
    @Override
    public Character createEntity(EntityEnum type, String title) {
        switch(type) {
            case RUNNER:
                return new Runner(this.id, this.dungeonRef,title);
            case BRAWLER:
                return new Brawler(this.id, this.dungeonRef,title);
            case THIEF:
                return new Thief(this.id, this.dungeonRef,title);
            case SNEAKER:
                return new Sneaker(this.id, this.dungeonRef,title);
            case NULL:
                return new Sneaker(this.id, this.dungeonRef,title);
            case BLINKER:
                return new Sneaker(this.id, this.dungeonRef,title);
            case SEEKER:
                return new Sneaker(this.id, this.dungeonRef,title);
            case ORBITER:
                return new Sneaker(this.id, this.dungeonRef,title);
            default:
                return new Sneaker(this.id, this.dungeonRef,title);
    
        }
    }


    /**
     * @param copiesint
     * @return ArrayList<Character>
     *
     * Creates an ArrayList of Characters.
     */
    public ArrayList<Character> entitySet(int copies){
        ArrayList<Character> characterList = new ArrayList<Character>();
        for (int i = 0; i < copies; i++) {
            characterList.add(new Runner(this.id, this.dungeonRef, ""));
            this.id++;
            characterList.add(new Sneaker(this.id, this.dungeonRef, ""));
            this.id++;
            characterList.add(new Thief(this.id, this.dungeonRef, ""));
            this.id++;
            characterList.add(new Brawler(this.id, this.dungeonRef, ""));
            this.id++;
        }
        return characterList;
    }
}
