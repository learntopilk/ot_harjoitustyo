# Instructions

Download the file [MRisk_1.1.jar](/home/joonas/kurssit/ot2018/ot_harjoitustyo/mrisk/MRisk/target/MRisk-1.0-SNAPSHOT.jar)

### Configuring the environment

No configurations should be needed (that I know of).

### Starting up the game

cd to the directory where the jar is located, and punch in the following spell:

```
java -jar MRisk_1.1.jar
```

This is what you will see:
![Start screen](startscreen.png)

Press _Start Game_ to `Start the Game`.

### Playing the game
You'll see this:
![Game screen](gamescreen.png)

Or almost. in the beginning, all countries are beige. Two players will pick countries one by one.

After all countries are selected, the troop value (not displayed yet) of each country will be added up. This is the number of troops each player can deploy.

Units are deployed one by one, turn by turn.

After all troops are deployed, the game will enter the attack phase. (**Note:** not implemented yet.)