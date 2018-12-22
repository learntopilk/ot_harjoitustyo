# MRisk architecture

MRisk is a Java-based Risk clone, made with JavaFX. It attempts to work with a layered architecture, as demonstrated below (Note that the section drawn with a dashed line has not been implemented):

![Architectural drawing](Arch.jpg)

## User Interface

The UI is built with JavaFX. Currently, there are three views:

1. Start screen
2. Game Screen
3. Instruction Screen 

Each of these is a Scene object, of which one is always set to the Stage of the application. Most of the UI lies in the class StartScreen.java.

While I have tried to keep application an UI logic isolated, this is not fully the case currently (it's not easy. Who knew?).

The UI calls the method that starts the game.

## Application Logic

![main classes](b168feca.png)

The class Game contains most of the controlling logic of the game. A central mechanic is that of game phases, of which there are three:

- Country selection
- Troop deployment
- Attack phase.


The class Player contains player-specific data.

Country.java takes care of countries, and manages the various country views by implementing event handler methods.

## Files

The software uses a file called _countries.csv_ for generating the main map of the game.

### Map generation

Countries/states and thus maps are stored as CSV files. The format is as follows: 

```
Florida;2;/florida.png;453d;408d;Alabama;Georgia;
Georgia;1;/georgia.png;289d;460d;Florida;Alabama;South%20Carolina;North%20Carolina;Tennessee;
```

The data is in the following order:
1. Name of the country/state - Spaces must be URL-encoded
2. Troop value - how many troops owning this state is worth
3. URI of the image that represents the state
4. Offset X value of the image on the screen
5. Offset Y value of the image on the screen
6. All Neighboring states for this particular country.

Hypothetically, this makes it easy to add new maps. However some parts of map generation are hard-coded still, so a small refactoring effort is required to accommodate for that. In addition, the file is part of the .jar for now.

## Sequence diagram

Thus we assign a country to a player:
![Sequence diagram](sequence.png)


## Weaknesses

- Players can attack whichever country they choose. We know what the neighbors of each country are, but as of now cannot do anything with that information.
- The code is messy and UI elements should be better separated from the application logic.