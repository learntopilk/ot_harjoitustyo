package game;

import DAO.CountryDAO;
import gui.StartScreen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Game {

    int players;
    Player p1;
    Player p2;
    Player currentPlayer;
    int round;
    private List<Country> countries;
    private List<Connection> connections;
    private Country selectedCountry;
    private String phase;
    private int countriesLeftToSelect;
    private int troopsLeftToDeploy;
    private CountryDAO countrySystem;
    private int roundNumber;
    private StartScreen ss;

    public static void main(String[] args) {

    }

    public Game() {
        this.countries = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.players = 2;
        this.p1 = new Player(Color.GREEN);
        this.p2 = new Player(Color.PURPLE);
        this.currentPlayer = p1;
        this.phase = "COUNTRYSELECTION";
        this.round = 1;
        this.troopsLeftToDeploy = 0;
        countrySystem = new CountryDAO(this);
        this.initializeCountries();
        this.roundNumber = 0;
    }

    /**
     * Begins the game by starting the country selection phase.
     */
    public void start() {
        this.startCountrySelection();
    }

    public void initializeCountries() {
        this.countries = countrySystem.readCountries();
    }

    public void attachView(StartScreen s) {
        this.ss = s;
    }

    /**
     * Begins the deployment phase. Can be called many times over a single game.
     * Also calculates the number of troops each player has to deploy.
     */
    public void startTroopDeployment() {
        phase = "DEPLOYMENT";
        this.updatePhaseDisplay();
        this.calculateTroopsToDeploy();
        currentPlayer = p1;
        this.ss.pd.setTurn(currentPlayer.getColor());
    }

    public void updatePhaseDisplay() {
        this.ss.updatePhaseDisplay(this.phase);

    }

    /**
     * Alternates between players in the deployment phase, or, if all troops
     * have been deployed, moves the game to the attack phase.
     */
    public void nextDeploymentTurn() {
        if (this.troopsLeftToDeploy > 0) {
            do {
                cyclePlayer();
            } while (this.currentPlayer.getTroopsToSet() == 0);
        } else {
            startAttackPhase();
        }
    }

    private void startAttackPhase() {
        phase = "ATTACK";
        this.updatePhaseDisplay();
        this.ss.pd.setTurn(currentPlayer.getColor());

    }

    public Country getCountryByName(String name) {
        for (Country c : countries) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * A helper function that indicates whether there are troops left to set.
     *
     * @return
     */
    public boolean troopsLeftToSet() {
        if (this.troopsLeftToDeploy > 0) {
            return true;
        }
        return false;
    }

    /**
     * Reduces the total number of troops left to deploy by one, or returns
     * false if the number was already 0,
     *
     * @return If there were troops to remove, returns true. Else returns false.
     */
    public boolean reduceTotalNumberOfTroopsLeftToDeploy() {
        if (this.troopsLeftToDeploy > 0) {
            this.troopsLeftToDeploy--;
            return true;
        }
        return false;
    }

    public int getNumberOfTroopsLeftToDeploy() {
        return this.troopsLeftToDeploy;
    }

    /**
     * Begins the country selection phase. Can only be called once per game.
     */
    public void startCountrySelection() {
        phase = "COUNTRYSELECTION";
        //this.updatePhaseDisplay();

        countriesLeftToSelect = this.countries.size();
        currentPlayer = p1;
        this.ss.pd.setTurn(currentPlayer.getColor());
        this.ss.pd.updatePhase(this.getPhase());
    }

    /**
     * Calculates the number of troops each player can deploy on the next turn,
     * based on the values of the countries each player owns.
     */
    public void calculateTroopsToDeploy() {
        this.troopsLeftToDeploy = 0;
        while (p1.getTroopsToSet() > 0) {
            p1.removeOneTroop();
        }
        while (p2.getTroopsToSet() > 0) {
            p2.removeOneTroop();
        }
        for (Country c : this.countries) {
            Player p = c.getOwner();
            p.addTroopsToSet(c.getTroopValue());
            this.troopsLeftToDeploy += c.getTroopValue();
        }
        this.updateTroopDisplays();
    }

    /**
     * A method that completes all actions required for ending a round.
     */
    public void advanceRound() {
        if (this.roundNumber == 0) {
            this.cyclePlayer();
            this.roundNumber++;
        } else {
            this.roundNumber = 0;
            this.startTroopDeployment();
        }
        if (this.selectedCountry != null) {
            this.selectedCountry.deselect();
        }
    }

    /**
     * Selects the next player in line.
     */
    public void cyclePlayer() {
        if (this.currentPlayer == p1) {
            this.currentPlayer = p2;
        } else {
            this.currentPlayer = p1;
        }
        this.ss.pd.setTurn(currentPlayer.getColor());
    }

    public void updateTroopDisplays() {
        this.ss.pd.updatePlayer1TroopsToSet(p1.getTroopsToSet());
        this.ss.pd.updatePlayer2TroopsToSet(p2.getTroopsToSet());
    }

    /**
     * Adds a country to the game's list of countries.
     *
     * @param c The Country to add to the list.
     */
    public void addCountry(Country c) {
        this.countries.add(c);
        this.countriesLeftToSelect++;
    }

    /**
     * Allows access to the list of countries currently in the game.
     *
     * @return List of countries
     */
    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * Returns a country by index (its index number in the list of countries).
     *
     * @param index
     * @return Country at the specified index
     */
    public Country getCountry(int index) {
        return this.countries.get(index);
    }

    /**
     * Subtracts one from the number of countries that do not have an owner.
     *
     * @return true if countries remain after the operation, false if all
     * countries have been assigned to an owner.
     */
    public boolean removeCountryToSet() {
        this.countriesLeftToSelect--;
        if (countriesLeftToSelect > 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of countries that are not owned by any player.
     *
     * @return Number of countries that currently do not have an owner
     */
    public int getNumberOfCountriesRemainingToSelect() {
        return this.countriesLeftToSelect;
    }

    /**
     *
     * @return The country that is currently selected by the player in turn.
     */
    public Country getSelectedCountry() {
        return this.selectedCountry;
    }

    public void selectCountry(Country c) {
        this.selectedCountry = c;
    }

    /**
     * Deselects the specified country.
     *
     * @param index Country to deselectd
     */
    public void deselectCountry(int index) {
        this.selectedCountry = null;
    }

    public void removeSelectedCountry() {
        this.selectedCountry = null;
    }

    /**
     *
     * @return The player set as having their turn.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Returns a string representing the current game cycle.
     *
     * @return The current phase. Possible values are DEPLOYMENT,
     * COUNTRYSELECTION, and ATTACK.
     */
    public String getPhase() {
        return this.phase;
    }

    public boolean moreThanOnePlayerLeft() {
        int player1 = 0;
        int player2 = 0;
        for (Country c : this.countries) {
            if (c.getOwner() == this.p1) {
                player1++;
            }
            if (c.getOwner() == this.p2) {
                player2++;
            }
        }
        return (player1 > 0 && player2 > 0);
    }

    public void endGame(Player winner) {
        // END THE GAME
        this.phase = "GAME OVER";
        this.updatePhaseDisplay();
        System.out.println("The winner is " + winner.getName() + "!");
    }
}
