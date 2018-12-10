
package game;

import java.util.ArrayList;
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
    private Country selectedCountry;
    private String endButtonText;
    private String phase;
    private int countriesLeftToSelect;
    private int troopsLeftToDeploy;

    public static void main(String[] args) {

    }

    public Game() {
        this.countries = new ArrayList<>();
        players = 2;
        p1 = new Player(Color.GREEN);
        p2 = new Player(Color.PURPLE);
        currentPlayer = p1;
        phase = "COUNTRYSELECTION";
        round = 1;
        troopsLeftToDeploy = 0;

    }
/*
    public void attack(int attacker, int defender) {
        // TODO
    }
*/
    public void start() {
        this.startCountrySelection();
    }

    // GAME PHASE CYCLES
    public void startTroopDeployment() {
        phase = "DEPLOYMENT";
        this.calculateTroopsToDeploy();
        currentPlayer = p1;
        // PLACING TROOPS
    }

    public void startAttackPhase() {
        phase = "ATTACK";
    }
    
    public boolean troopsLeftToSet() {
        if (this.troopsLeftToDeploy > 0) return true;
        return false;
    }
    
    public void reduceTotalNumberOfTroopsLeftToDeploy() {
        if (this.troopsLeftToDeploy > 0) this.troopsLeftToDeploy--;
    }

    public void startCountrySelection() {
        phase = "COUNTRYSELECTION";
        countriesLeftToSelect = 4;
        currentPlayer = p1;

    }

    public void endTurn() {
        round++;
        if (round > 2) {
            round = 1;
        }
    }

    /**
     * Calculates the number of troops each player can deploy on the next turn, based on the 
     * values of the countries each player owns.
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
     * INCOMPLETE A method that completes all actions required for ending a round.
     */
    public void advanceRound() {
        cyclePlayer();
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
     * @param c The Country to add to the list.
     */
    public void addCountry(Country c) {
        this.countries.add(c);
    }

    /**
     * Allows access to the list of countries currently in the game.
     * @return List of countries
     */
    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * Returns a country by index (its index number in the list of countries).
     * @param index
     * @return Country at the specified index
     */
    
    public Country getCountry(int index) {
        return this.countries.get(index);
    }
    

    /**
     * Subtracts one from the number of countries that do not have an owner.
     * @return true if countries remain after the operation, false if all countries have been assigned to an owner.
     */
    public boolean removeCountryToSet() {
        this.countriesLeftToSelect--;
        if (countriesLeftToSelect > 0) {
            return true;
        }
        return false;
    }
    
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

    /**
     * Sets a country as selected by the player currently in turn.
     * @param index Index of the country to select
     */
    public void selectCountry(int index) {
        Country oldCountry = this.selectedCountry;
        if (oldCountry != null) {
            oldCountry.deselect();
        }

        this.selectedCountry = getCountry(index);
        this.selectedCountry.select();
    }

    
    /**
     * Deselects the specified country.
     * @param index Country to deselectd
     */
    public void deselectCountry(int index) {
        if (this.selectedCountry == null) {
            return;
        }
        this.selectedCountry.deselect();
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
     * @return The current phase. Possible values are DEPLOYMENT, COUNTRYSELECTION, and ATTACK.
     */
    public String getPhase() {
        return this.phase;
    }
}
