# MRisk

MRisk is a minimal implementation of the classic board game Risk. As a minimal requirement, the game will allow two human players to play a full game of Risk against each other in the Southeastern United States.


## Recent development

At the moment, the game has two players, four states, and players may pick states. Players may attack each other, conquer states, and move their troops around the map (a bit too liberally, but no time to fix it now).



## Release

Check out my latest release [here](https://github.com/learntopilk/ot_harjoitustyo/releases/tag/viikko5). It's still terrible, but hey, at least you can pick countries.


## Documentation

[Instructions](https://github.com/learntopilk/ot_harjoitustyo/blob/master/dokumentointi/Kayttoohje.md)

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