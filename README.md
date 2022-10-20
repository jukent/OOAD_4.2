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

...

## Identified OO Patterns

**Command Pattern**

The Command pattern is used to control the character. Each command such as the move, flee, celebrate, search for treasure, and fight inherit from a general command abstract class. The Invoker, contains all the logic for the player interacting with the game and choosing actions for the character. Each execute function is responsible for gathering the players input to turn into a command that goes to the main game engine. The game engine acts as the reciever, and the character at times also acts as the reciever. 

**Singleton Pattern**

**Factory Pattern**
The factory pattern was implemented in the character and creature spawning. Each factory such as creature factory and character factory have a method labeled CharacterFactory and Creature Factory. These could have been extended from an EntityFactory, but we ran into some inheritance and polymorphism issues when using this (especially with array lists). Each character and creature type has a subclassed factory. A Facade pattern holds a reference to every subclassed factory, and is an easy way to spawn in characters and creatures.

## Changes to UML Diagram

Changes from the UML class diagram from 4.1 shows that the factory implementation is now shown correctly, but due to the amount of subclasses of factories, a facade was implemented to deal with the complicated nature of spawning in creatures and characters. 

## Assumptions Made

...


## JUnit Testing

Testing is now done automatically on every new push to the GitHub repository with GitHub actions Continuous Integration.
The steps for this are in [`.github/workflows/junit_test.yml`](https://github.com/jukent/OOAD_4.2/blob/main/.github/workflows/junit_tests.yaml) and can be viewed in the "Actions" tab.

...

## Java-based Line Graph

...

## Citations

...
