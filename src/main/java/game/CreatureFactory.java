package game;
import java.util.ArrayList;
import entity.*;
import dungeon.*;

public class CreatureFactory extends Factory{


    /**
     * @param dungeonRef Dungeon
     *
     * Constructor for the Creature Factory.
     */
    public CreatureFactory(final Dungeon dungeonRef) {
        this.dungeonRef = dungeonRef;
    }


    /* (non-Javadoc)
     * @see game.Factory#createEntity(entity.EntityEnum, java.lang.String)
     *
     * Creates the Creatures.
     */
    @Override
    public Creature createEntity(EntityEnum type, String title){
        switch(type){
            case RUNNER:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case BRAWLER:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case THIEF:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case SNEAKER:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case NULL:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case BLINKER:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
            case SEEKER:
                this.id++;
                return new Seeker(this.id, this.dungeonRef);
            case ORBITER:
                this.id++;
                return new Orbiter(this.id, this.dungeonRef);
            default:
                this.id++;
                return new Blinker(this.id, this.dungeonRef);
        }
    }


    /**
     * @param copies int
     * @return ArrayList<Creature>
     *
     * Creates an ArrayList of Creatures based on how many copies
     * are requested.
     */
    public ArrayList<Creature> entitySet(int copies) {
        ArrayList<Creature> creaturelist = new ArrayList<Creature>();
        for (int i = 0; i < copies; i++) {
            creaturelist.add(new Blinker(this.id, this.dungeonRef));
            this.id++;
            creaturelist.add(new Orbiter(this.id, this.dungeonRef));
            this.id++;
            creaturelist.add(new Seeker(this.id, this.dungeonRef));
            this.id++;
        }
        return creaturelist;
    }
}
