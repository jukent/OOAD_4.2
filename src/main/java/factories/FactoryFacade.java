package factories;

import java.util.ArrayList;

import dungeon.Dungeon;
import entity.Character;
import entity.Creature;
import entity.CharacterEnum;
import entity.CreatureEnum;


public class FactoryFacade {
    CreatureFactory BlinkerFact;
    CreatureFactory SeekerFact;
    CreatureFactory OrbiterFact;
    CharacterFactory BrawlerFact;
    CharacterFactory RunnerFact;
    CharacterFactory ThiefFact;
    CharacterFactory SneakerFact;
    Dungeon dungeonRef;

    //Facade pattern to interact with complicated factories
    public FactoryFacade(Dungeon dungeon){
        dungeonRef = dungeon;
        BlinkerFact = new BlinkerFactory(dungeon);
        SeekerFact = new SeekerFactory(dungeon);
        OrbiterFact = new OrbiterFactory(dungeon);
        BrawlerFact = new BrawlerFactory(dungeon);
        RunnerFact = new RunnerFactory(dungeon);
        ThiefFact = new ThiefFactory(dungeon);
        SneakerFact = new SneakerFactory(dungeon);
    }

    public Character spawnCharacter(CharacterEnum type,String Name){
        switch(type){
            case BRAWLER:
                return BrawlerFact.spawnCharacter(Name);
            case RUNNER:
                return RunnerFact.spawnCharacter(Name);
            case THIEF:
                return ThiefFact.spawnCharacter(Name);
            case SNEAKER:
                return SneakerFact.spawnCharacter(Name);
            default:
                return BrawlerFact.spawnCharacter(Name);
        }


    }

    public Creature spawnCreature(CreatureEnum type){
        switch(type){
            case ORBITER:
                return OrbiterFact.spawnCreature();
            case SEEKER:
                return OrbiterFact.spawnCreature();
            case BLINKER:
                return OrbiterFact.spawnCreature();
            default:
                return BlinkerFact.spawnCreature();
        }

        
    }


    public ArrayList<Creature> spawnCreatureSet(int copies){

        ArrayList<Creature> tempList = new ArrayList<Creature>();

        for(int i = 0; i < copies; i++){
            tempList.add(spawnCreature(CreatureEnum.ORBITER));
            tempList.add(spawnCreature(CreatureEnum.SEEKER));
            tempList.add(spawnCreature(CreatureEnum.BLINKER));
        }

        return tempList;

    }


}
