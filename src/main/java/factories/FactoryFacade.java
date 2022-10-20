package factories;

import java.util.ArrayList;

import dungeon.Dungeon;
import entity.Character;
import entity.Creature;
import entity.CharacterEnum;
import entity.CreatureEnum;


public class FactoryFacade {
    CreatureFactory blinkerFact;
    CreatureFactory seekerFact;
    CreatureFactory orbiterFact;
    CharacterFactory brawlerFact;
    CharacterFactory runnerFact;
    CharacterFactory thiefFact;
    CharacterFactory sneakerFact;
    Dungeon dungeonRef;


    //Facade pattern to interact with complicated factories
    /**
     * @param dungeon Dungeon
     *
     * Facade for the factories.
     */
    public FactoryFacade(final Dungeon dungeon) {
        dungeonRef = dungeon;
        blinkerFact = new BlinkerFactory(dungeon);
        seekerFact = new SeekerFactory(dungeon);
        orbiterFact = new OrbiterFactory(dungeon);
        brawlerFact = new BrawlerFactory(dungeon);
        runnerFact = new RunnerFactory(dungeon);
        thiefFact = new ThiefFactory(dungeon);
        sneakerFact = new SneakerFactory(dungeon);
    }


    /**
     * @param type CharacterEnum
     * @param name String
     * @return Character
     *
     * Spawns a Character.
     */
    public Character spawnCharacter(final CharacterEnum type,
        final String name) {
            switch (type) {
                case BRAWLER:
                    return brawlerFact.spawnCharacter(name);
                case RUNNER:
                    return runnerFact.spawnCharacter(name);
                case THIEF:
                    return thiefFact.spawnCharacter(name);
                case SNEAKER:
                    return sneakerFact.spawnCharacter(name);
                default:
                    return brawlerFact.spawnCharacter(name);
            }
    }


    /**
     * @param type CreatureEnum
     * @return Creature
     *
     * Spawns a Creature.
     */
    public Creature spawnCreature(final CreatureEnum type) {
        switch (type) {
            case ORBITER:
                return orbiterFact.spawnCreature();
            case SEEKER:
                return seekerFact.spawnCreature();
            case BLINKER:
                return blinkerFact.spawnCreature();
            default:
                return blinkerFact.spawnCreature();
        }
    }


    /**
     * @param copies Integer
     * @return ArrayList<Creature>
     *
     * Spawns the Creatures.
     */
    public ArrayList<Creature> spawnCreatureSet(
        final int copies) {

            ArrayList<Creature> tempList = new ArrayList<Creature>();

            for (int i = 0; i < copies; i++) {
                tempList.add(spawnCreature(CreatureEnum.ORBITER));
                tempList.add(spawnCreature(CreatureEnum.SEEKER));
                tempList.add(spawnCreature(CreatureEnum.BLINKER));
            }

            return tempList;
    }
}
