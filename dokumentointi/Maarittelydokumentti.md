# Vaatimusmäärittely

## Misk

..eli Mini-Risk.

### Sovelluksen tarkoitus

Misk on Risk-klooni. Pelaajat hyökkäävät toisiaan vastaan vallatakseen alueita. Pelaajat saavat joukkoja kunkin vuoron alussa hallussa pitämiensä alueiden arvon mukaisesti. Peli loppuun kun jäljellä on yksi pelaaja.

Pelaaja voi hyökätä mistä tahansa omistamastaan ruudusta sen viereiseen ruutuun.

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

Sovelluksessa on kolme päänäkymää: Aloitusruutu, peliruutu sekä tilastoruutu.