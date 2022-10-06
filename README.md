# OOAD_3.2
[![Java CI](https://github.com/jukent/OOAD_3.2/actions/workflows/junit_tests.yaml/badge.svg)](https://github.com/jukent/OOAD_3.2/actions/workflows/junit_tests.yaml)
[![Lint Code Base](https://github.com/jukent/OOAD_3.2/actions/workflows/super_linter.yaml/badge.svg)](https://github.com/jukent/OOAD_3.2/actions/workflows/super_linter.yaml)

## OOAD5448: Raiders of the Lost Arctangent
by David Chaparro and Julia Kent

Java version: 17.0.1

Expanding the Raiders of the Lost Arctangent Game to use specific treasures and design patterns. Previous iteration can be found at [OOOAD5448_P2.2](https://github.com/jukent/OOAD5448_P2.2).

--------------------------------

This project is a Java text-based adventure game where 4 classes of characters (Brawlers, Sneakers, Runners, and Thieves) encounter 3 classes of Creatures (Blinkers, Orbiters, and Seekers) in a 4-level 3x3 dungeon.
The game ends when either: the characters have collectively found all 24 treasures (win), all the creatures are defeated (win), or all of the characters are defeated (lose).

At this iteration all character decisions and movement patterns are randomized.

Characters:
- Brawlers: +2 Strength Buff
- Sneakers: 50% Chance of Evasion
- Runners: +1 Move
- Thieves: +1 Strength Buff,  +1 Treasure Hunting Buff

Creatures:
- Blinkers: Begin on 4th level, move by "blinking" to any random room.
- Orbiters: Begin on any level and "orbit" its outer rooms. Can move clockwise or counterclockwise.
- Seekers: Begin on any level and awaits a character in a nearby room, then "seek out" a battle by moving towards the character.

Treasures:
- Sword: +1 Strength Buff
- Gem: +1 Strength Buff to Creatures
- Armor: -1 Strength Buff to Creatures
- Portal (consumable): Allows Characters to "blink" for their movement pattern
- Trap (consumable, immediate):  -1 Health
- Potion (consumable): +1 Health

**Each Character may only hold 1 Treasure of each type at a time.


## How to Run

To run the game, execute `Main.java` within the project. The terminal will then prompt you to press 'Enter' to begin the game.

## Results

Captured output for a single simulated game is in [`SingleGameRun.txt`](https://github.com/jukent/OOAD_3.2/blob/main/SingleGameRun.txt) <br/>
All Tracker ouptuts for this single run are captures in [`Logger-files/Logger-n.txt` files](https://github.com/jukent/OOAD_3.2/tree/main/Logger-files). <br/>
Final summary captured output from 30 runs is in [`MultipleGameRun.txt`](https://github.com/jukent/OOAD_3.2/blob/main/MultipleGameRun.txt) <br/>

The results of the 30-game runs can be summarized as: <br/>
Characters won by finding 24 Treasures 0 times. <br/>
Characters won by defeating all creatures 23 times. <br/>
Characters lost by all being defeated 7 time. <br/>

From these results we can see that the new Treasure implementations made finding all Treasures much less likely.
Characters are rarely defeated, perhaps this could be balanced by adding more Creatures to the Dungeon or increasing Creature attack power.

## Identified OO Patterns

**Strategy** pattern was used in encapsalating fighting, treasure hunting, and movement behaviors into their own classes.

In order to encapsalte movement behavior and allow Characters to now "Blink", we created an `Entity` superclass. Now the same `move()` method can be called on Characters or Creatures.

**Observer** pattern is demonstrated most clearly through the new `Tracker` and `Logger` Classes.
The Tracker is an observer that is notified by various events (creature/character win/loss/movement/celebration/treasure hunt, etc).
When these events are published, the `Tracker` stores the new data appropriately.
The `Logger` and the `Printer` are two subscribers that log or print the Tracker's stored data respectively.

The `Tracker` also lets the `Room` objects know that their occupation has changed whenever Entities move, so Rooms are a subscriber.
This cleanly solves the problem of having Rooms store occupancy and Entities storing location that was done a ungracefully in Project 2.2.
This is convenient for the `Seeker`'s movement patterns.

It was tricky to decide what the `Tracker` should be responsible for in the the Observer pattern.
Characters are still responsible for their own attributes, that information doesn't need to be duplicated.
We made sure all of the assignment-specified events were published to the Tracker, and beyond that tried to think about what is useful or what problems Observer could solve.

**Decorator** pattern is used with Celebrations whenever a Character wins a fight.


## Changes to UML Diagram

We did not anticipate the number of methods that `Tracker` and `Logger` would use, how we would rename some methods, classes, and variables, or how we would move some code around.
Due to the Observer pattern, the `Printer` class now has more methods that were previously the responsiblity of the `GameEngine`. We also added a `PrinterColumns` Class to help clean up the printing method.
The biggest difference is the creation of the `Entity` superclass. We did not predict how useful that would be for encapsulating movement patterns during the design/planning phase for this project.
For comparison here is the [original UML](https://github.com/jukent/OOAD_3.2/blob/main/ClassDiagram_3.1.png) and the [updated UML](https://github.com/jukent/OOAD_3.2/blob/main/ClassDiagram_3.2UPDATED.png)

Getters and Setters are left off of the UML diagram for readability.

## Assumptions Made

The instructions were unspecified on if there should be a decision tree for Character movement when in possession of a Portal Treasure, so we decided to make the simple assumption that Characters always move by Portaling (the "Blink" MovementType).

## JUnit Testing

Testing was origionally done with Maven for Java via the ["Test Runner for Java" extension](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) within Visual Studio Code.
This required we set up a [`vscode/settings.json`](https://github.com/jukent/OOAD_3.2/blob/main/.vscode/settings.json) file pointing to our `main/` and `test/` directories and a [`pom.xml`](https://github.com/jukent/OOAD_3.2/blob/main/pom.xml) file specifying that we use JUnit version 4.13.2 and that our tests are in the [`/src/test/java` folder](https://github.com/jukent/OOAD_3.2/tree/main/src/test/java).
Results from these tests are shown passing in the Visual Studio Code interface in [test suite screenshot](maven_tests_passing.png).

Tests were useful for testing edge cases (such as Seeker movement and Character with Portal movement) because they allowed us to set up the scenarios and assert the correct outcome, as opposed to running the simulation repeatedly watching for the Seeker to move.

Some methods that were previously hidden had to be exposed as "public" for the testing framework to be able to access them.
This is permissable, but ideally we'd want a Testing framework that does not interfere with how the codebase was originally designed.

We have 26 tests, spanning movement, treasure hunting, celebration, fighting, treasures, and the game engine.
Ideally we'd strive for more code coverage, but we tested the most suspect areas of the code and are satisfied for this assignment.

Testing is now done automatically on every new push to the GitHub repository with GitHub actions Continuous Integration.
The steps for this are in [`.github/workflows/junit_test.yml`](https://github.com/jukent/OOAD_3.2/blob/main/.github/workflows/junit_tests.yaml) and can be viewed in the "Actions" tab.
[Here are recent test results](https://github.com/jukent/OOAD_3.2/actions/runs/3187557507/jobs/5199243529).

Similiarly, we use a GitHub Actions linter to enforce consistent style guide and standard rules. Here is an example [Linter test results](https://github.com/jukent/OOAD_3.2/actions/runs/3187557506/jobs/5199243526).

## Citations

Testing possibilities were learned from this [JUnit documentation](https://junit.sourceforge.net/javadoc/org/junit/Assert.html).

We used these [instructions for setting up testing](https://code.visualstudio.com/docs/java/java-testing).

This resource was useful for understanding the Observer pattern: ["Game Programming Patterns" a book by Robert Nystrom](http://gameprogrammingpatterns.com/observer.html).

The `PrinterColumns` class was almost one-to-one borrowed from candied_orange's response to the Stack Overflow question ["Is there an easy way to output two columns to the console in Java?"](https://stackoverflow.com/questions/699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java).
We added documentation via comments and Javadocs were added to show that we understand the borrowed code.

[GitHub actions instructions for testing with Maven](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven).

[GitHub actions instructions for linter](https://github.com/github/super-linter).
