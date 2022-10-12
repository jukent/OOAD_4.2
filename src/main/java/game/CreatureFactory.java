package game;
import java.util.ArrayList;
import entity.*; 
import dungeon.*;

public class CreatureFactory extends Factory{
    
CreatureFactory(Dungeon dungeonRef){
    this.dungeonRef = dungeonRef;

}

Creature CreateEntity(EntityEnum type, String Title){
    switch(type){
        case RUNNER:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
        case BRAWLER:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
        case THIEF:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
        case SNEAKER:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
        case NULL:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
        case BLINKER:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);  
        case SEEKER:
            this.ID++;
            return new Seeker(this.ID, this.dungeonRef);
        case ORBITER:
            this.ID++;
            return new Orbiter(this.ID, this.dungeonRef);
        default:
            this.ID++;
            return new Blinker(this.ID, this.dungeonRef);
    }
    
}


ArrayList<Creature> EntitySet(int copies){
    ArrayList<Creature> creaturelist = new ArrayList<Creature>();
    for(int i = 0; i<copies; i++){
    creaturelist.add(new Blinker(this.ID, this.dungeonRef));
    this.ID++;
    creaturelist.add(new Orbiter(this.ID, this.dungeonRef));
    this.ID++;
    creaturelist.add(new Seeker(this.ID, this.dungeonRef));
    this.ID++;}
    return creaturelist;
}


}

