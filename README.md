# OOAD_4.2
[![Java CI](https://github.com/jukent/OOAD_4.2/actions/workflows/junit_tests.yaml/badge.svg)](https://github.com/jukent/OOAD_4.2/actions/workflows/junit_tests.yaml)
[![Lint Code Base](https://github.com/jukent/OOAD_4.2/actions/workflows/super_linter.yaml/badge.svg)](https://github.com/jukent/OOAD_4.2/actions/workflows/super_linter.yaml)

## OOAD5448: Raiders of the Lost Arctangent
by David Chaparro and Julia Kent

Java version: 17.0.1

Expanding the Raiders of the Lost Arctangent Game to use an adventurer interface. Previous iteration can be found at [OOOAD_3.2](https://github.com/jukent/OOAD_3.2).

--------------------------------

This project is a Java text-based adventure game where a playe rchoses from 4 classes of adventures (Brawlers, Sneakers, Runners, and Thieves)
and encounters 3 classes of Creatures (Blinkers, Orbiters, and Seekers) in a 4-level 3x3 dungeon.
The game ends when the adventurer leaves the dungeon (returns to Room: "(0-1-1)") or when the adventurer dies.
When leaving the win/lose conditions are as follows:
- the player has found each type of treasure (win),
- the player has defeated all of the creatures (win),
- the adventurer exits without having found each type of treasure (lose),
- or the adventurer is defeated (lose).

Characters:
- Brawlers: +2 Strength Buff, 12 HP
- Sneakers: 50% Chance of Evasion, 8HP
- Runners: Can move up to 2 spaces per turn, 10HP
- Thieves: +1 Strength Buff,  +1 Treasure Hunting Buff, 10HP

Creatures:
- Blinkers: Begin on 4th level, move by "blinking" to any random room.
- Orbiters: Begin on any level and "orbit" its outer rooms. Can move clockwise or counterclockwise.
- Seekers: Begin on any level and awaits a character in a nearby room, then "seek out" a battle by moving towards the character.

Treasures:
- Sword: +1 Strength Buff
- Gem: +1 Strength Buff to Creatures
- Armor: -1 Strength Buff to Creatures
- Portal: Allows Characters to "blink" for their movement pattern, causes an immediate "blink"
- Trap (consumable, immediate):  -1 Health
- Potion (consumable): +1 Health

**Each Character may only hold 1 Treasure of each type at a time.


## How to Run

To run the game, execute `Main.java` within the project. The terminal will then prompt you to press 'Enter' to begin the game.

Each turn you will be asked to chose a command for the adventurer (Fight, Move, Celebrate, or Search) via prompts in the console.


## Results

Results from a run are shown in the [`SingleGameRun.txt`](https://github.com/jukent/OOAD_4.2/blob/main/SingleGameRun.txt) file and the [Logger files folder](https://github.com/jukent/OOAD_4.2/blob/main/Logger-files/).
These results correspond to line graph in [`GameLineGraph.png`](https://github.com/jukent/OOAD_4.2/blob/main/GameLineGraph.png).


## Identified OO Patterns

**Command Pattern** is used to control the character. Each command such as the move, flee, celebrate, search for treasure, and fight inherit from a general command abstract class. The Invoker, contains all the logic for the player interacting with the game and choosing actions for the character. Each execute function is responsible for gathering the players input to turn into a command that goes to the main game engine. The game engine acts as the reciever, and the character at times also acts as the reciever. 

**Singleton Pattern** is used for the Logger and the Tracker. The Logger is lazy Singleton (waits till it is called and checks to see if it exists before returning itself) and the Tracker is eager Singleton (returns itself without any checks). This pattern ensures that there is only one instance of these classes which ensures nice memory usage and less room for errors. The Dungeon would have also been a good candidate for the Singleton class.

**Factory Pattern** was implemented in the character and creature spawning. Each factory such as creature factory and character factory have a method labeled CharacterFactory and Creature Factory. These could have been extended from an EntityFactory, but we ran into some inheritance and polymorphism issues when using this (especially with array lists). Each character and creature type has a subclassed factory. A Facade pattern holds a reference to every subclassed factory, and is an easy way to spawn in characters and creatures.


## Changes to UML Diagram

Changes from the UML class diagram from 4.1 shows that the factory implementation is now shown correctly, but due to the amount of subclasses of factories, a facade was implemented to deal with the complicated nature of spawning in creatures and characters. 
Initially we had the Singleton pattern drawn with two class boxes, but the pattern is simpler than that and only needed to edit the attributes and methods of the existing Logger and Tracker methods.


## Assumptions Made

We assumed that while the Portal instantly teleports the Character, the portal remains of use and the Character may choose to portal again and again each turn after that.


## JUnit Testing

Testing is now done automatically on every new push to the GitHub repository with GitHub actions Continuous Integration.
The steps for this are in [`.github/workflows/junit_test.yml`](https://github.com/jukent/OOAD_4.2/blob/main/.github/workflows/junit_tests.yaml) and can be viewed in the "Actions" tab.

In the [`pom.xml`](https://github.com/jukent/OOAD_4.2/blob/main/pom.xml) file you can see that JUnit version 4.11 is used.

We ran 21 tests of movement, fighting, and treasure hunting.

A screenshot of the passing tests in the Visual Studio Code Maven test suite is attached in [`passing_test.png`](https://github.com/jukent/OOAD_4.2/blob/main/passing_tests.png).


## Java-based Line Graph

For the Line Graph we used XChart version 3.8.1. This too can be seen in the `pom.xml` file.

The only real challenge was reading the information from the Logger files to create the arrays used in plotting.
For this reason, we changed the Logger files to be `.json` formatted, so that data could be grabbed by key instead of by character index - which didn't seem very robust.
We assumed that this would be acceptable since traditionally, Logger files are made for machine readability more so than human-readability (which the original format favored).


## Citations

Help with using XChart from [Knowm "XChart Example Code"](https://knowm.org/open-source/xchart/xchart-example-code/).

Help with JSON files from [Mkyong.com "JSON-simple: How to Parse JSON"](https://mkyong.com/java/json-simple-how-to-parse-json/)
and [How to Do in Java: GSON Serialize and Deserialize](https://howtodoinjava.com/gson/gson-serialize-deserialize-hashmap/).
