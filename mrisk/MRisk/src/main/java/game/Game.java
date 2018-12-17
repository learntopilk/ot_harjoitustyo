package game;

import DAO.CountryDAO;
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
    private String endButtonText;
    private String phase;
    private int countriesLeftToSelect;
    private int troopsLeftToDeploy;
    private CountryDAO countrySystem;
    private int roundNumber;

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
        System.out.println("INITIALIZING");
        this.countries = countrySystem.readCountries();
    }

    /**
     * Begins the deployment phase. Can be called many times over a single game.
     * Also calculates the number of troops each player has to deploy.
     */
    public void startTroopDeployment() {
        phase = "DEPLOYMENT";
        this.calculateTroopsToDeploy();
        currentPlayer = p1;
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
        countriesLeftToSelect = this.countries.size();
        currentPlayer = p1;

    }

    /**
     * Calculates the number of troops each player can deploy on the next turn,
     * based on the values of the countries each player owns.
     */
    public void calculateTroopsToDeploy() {
        for (Country c : this.countries) {
            Player p = c.getOwner();
            p.addTroopsToSet(c.getTroopValue());
            this.troopsLeftToDeploy += c.getTroopValue();
        }

        System.out.println("Player 1 has " + p1.getTroopsToSet() + " troops to deploy");
        System.out.println("Player 2 has " + p2.getTroopsToSet() + " troops to deploy");
        System.out.println("There area a total of " + this.troopsLeftToDeploy + " = " + (p1.getTroopsToSet() + p2.getTroopsToSet()) + " troops to deploy");
    }

    /**
     * INCOMPLETE A method that completes all actions required for ending a
     * round.
     */
    public void advanceRound() {
        if (this.roundNumber == 0) { // later on, players.length;
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
}
