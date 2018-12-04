# MRisk

MRisk is a minimal implementation of the classic board game Risk. As a minimal requirement, the game will allow two human players to play a full game of Risk against each other in the Southeastern United States.

I hope to be able to add more/larger maps once the map mechanics are better defined.

## Recent development

At the moment, the game has two players, four states, and players may pick states. The actual game mechanics still need to be built.

The next steps::

1. Enabling troop deployment
2. Display for showing the number of troops each state has
3. Attack mechanics
4. Victory conditions
5. All of the states in the map included
6. Possibility for additional players.


## Release

Check out my latest release [here](https://github.com/learntopilk/ot_harjoitustyo/releases/tag/viikko5). It's still terrible, but hey, at least you can pick countries.


## Documentation

Instructions (TODO)

[Project requirements](https://github.com/learntopilk/ot_harjoitustyo/blob/master/dokumentointi/Maarittelydokumentti.md) (In Finnish)

[Project architecture](https://github.com/learntopilk/ot_harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md) (i.e. arkkitehtuuri.md)

[Mandatory sequence diagram](https://github.com/learntopilk/ot_harjoitustyo/blob/master/dokumentointi/sequence.png)

About tests

[Time spent](https://github.com/learntopilk/ot_harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Command Line magic

For running all of these, you need to be in the directory /mrisk/MRisk.

#### Testing

You can run tests with 
```
mvn test
```

Generate a test coverage report with 
```
mvn jacoco:report
```

#### Checkstyle

Check out my sweet checkstyle by checking in the following spell:

```
mvn jxr:jxr checkstyle:checkstyle
```

#### Generate jar

Use this to generate a functioning jar package:
```
mvn package
```