# MRisk architecture

MRisk is a Java-based Risk clone, made with JavaFX. It attempts to work with a layered architecture, as demonstrated below (Note that the section drawn with a dashed line has not been implemented):

![Architectural drawing](Arch.jpg)

## User Interface

The UI is built with JavaFX. Currently, there are three views:

1. Start screen
2. Game Screen
3. Stat Screen (with no information as of yet).

Each of these is a Scene object, of which one is always set to the Stage of the application. Most of the UI lies in the class StartScreen.java.

While I have tried to keep application an UI logic isolated, this is not fully the case currently (it's not easy. Who knew?).

The UI calls the method that starts the game.

## Application Logic

The class Game contains most of the controlling logic of the game. A central mechanic is that of game phases, of which there are three:

- Country selection
- Troop deployment
- Attack phase.



The class Player contains player-specific data.

Country.java takes care of countries, and manages the various country views by implementing event handler methods.

##Sequence diagram

Thus we assign a country to a player:
![Sequence diagram](sequence.png)

## Weaknesses

At this time, countries cannot be created with a single method, and they are created in the class StartScreen.java. This is not optimal, and I intend to move country data & creation to a separate module ASAP.