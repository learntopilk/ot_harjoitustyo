# Vaatimusmäärittely: Misk

..eli Mini-Risk.

### Sovelluksen tarkoitus

Misk on Risk-klooni. Pelaajat hyökkäävät toisiaan vastaan vallatakseen alueita. Pelaajat saavat joukkoja kunkin vuoron alussa hallussa pitämiensä alueiden arvon mukaisesti. Peli loppuun kun jäljellä on yksi pelaaja.

Pelaaja voi hyökätä mistä tahansa omistamastaan ruudusta sen viereiseen ruutuun.

Pelaajan täytyy pitää jokaisessa omistamassaan ruudussa vähintään yhtä yksikköä.

### Käyttäjät

Perussovellukseen ei tule nimimerkkiä monimutkaisempaa käyttäjätoimintoa. 

### Sovelluksen minimimuoto:

- Kaksi ihmispelaajaa pelaavat toisiaan vastaan
    - Pelaajat voivat asettaa nimimerkin
    - Pelaajat voivat valita puolensa värin
    - Pelaajat voivat valita aloitusalueensa
- Karttavaihtoehtoja on kaksi
- Peli pitää tilastoja käytetyistä vuoroista, menetetyistä yksiköistä ja voittajista
- Tilastot pitävät kirjaa 10 viimeisimmästä pelistä
- Pelin voi tallentaa ja ladata sitä jatkaakseen

### Pelin laajempi muoto (eli stretch goalit, jos aikaa riittää, prioriteettijärjestyksessa)

1. Ihmispelaajia voi olla 2-4 (tai miksei useampiakin)
2. Tallennettuja pelejä voi olla useita
3. Tilastoja tallennetaan esim. tietokantaan, josta niitä voidaan suodattaa esim. menetettyjen yksiköiden määrän mukaan
4. Ääniefektit ja taustamusiikki
5. Tietokone pelaa jonkintasoisella tekoälyllä

### Näkymät

Sovelluksessa on kolme päänäkymää
#### Aloitusruutu

Aloitusruudussa on pähee logo.
Aloitusruudun vasemmalla puoliskolla on:

- Start Game-painike
- Stats-painike, joka vie tilastoihin
- Quit -painike pelin sammuttamista varten.

Aloitusruudun oikealla puolella on
- select-boxit pelaajamäärälle,
- tekstikentät pelaajien nimimerkeille
- Pelaajien värin valinta.

Start game -painike aktivoituu, kun vähintään kahdella pelaajalla on nimi ja väri.

#### Peliruutu 

- Pelikenttä, jossa kartta
- kartalla alueita, jotka voi valita yhden kerrallaan joukkojen siirtoa varten
- Yhden ruudun ollessa valittuna korostuvat sen vieressä olevat ruudut (eli ruudut, joihin on mahdollista liikuttaa joukkoja)
- Oikealla yläkulmassa painike "Save game"
- Oikealla alakulmassa "Save and Quit", joka vie alkumenuun tallennettuaan pelin

#### Tilastoruutu

- Pelattujen pelien kokonaismäärä.
- Lista 10 viimeisimmistä peleistä: 
    - Pelaajien nimet
    - Pelaajien menettämät yksiköt
    - Voittaja
    - Vuorojen määrä.
- Vasemmassa yläkulmassa painike "Back to menu"
