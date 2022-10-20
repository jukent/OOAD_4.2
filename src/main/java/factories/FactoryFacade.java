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
    /**
     * @param dungeon Dungeon
     *
     * Facade for the factories.
     */
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


    /**
     * @param type CharacterEnum
     * @param name String
     * @return Character
     *
     * Spawns a Character.
     */
    public Character spawnCharacter(CharacterEnum type, String name) {
        switch (type) {
            case BRAWLER:
                return BrawlerFact.spawnCharacter(name);
            case RUNNER:
                return RunnerFact.spawnCharacter(name);
            case THIEF:
                return ThiefFact.spawnCharacter(name);
            case SNEAKER:
                return SneakerFact.spawnCharacter(name);
            default:
                return BrawlerFact.spawnCharacter(name);
        }
    }


    /**
     * @param type CreatureEnum
     * @return Creature
     *
     * Spawns a Creature.
     */
    public Creature spawnCreature(CreatureEnum type) {
        switch(type) {
            case ORBITER:
                return OrbiterFact.spawnCreature();
            case SEEKER:
                return SeekerFact.spawnCreature();
            case BLINKER:
                return BlinkerFact.spawnCreature();
            default:
                return BlinkerFact.spawnCreature();
        }
    }


    /**
     * @param copies Integer
     * @return ArrayList<Creature>
     *
     * Spawns the Creatures.
     */
    public ArrayList<Creature> spawnCreatureSet(int copies) {

        ArrayList<Creature> tempList = new ArrayList<Creature>();

        for (int i = 0; i < copies; i++) {
            tempList.add(spawnCreature(CreatureEnum.ORBITER));
            tempList.add(spawnCreature(CreatureEnum.SEEKER));
            tempList.add(spawnCreature(CreatureEnum.BLINKER));
        }

        return tempList;
    }
}
