package game;

import gui.CountryView;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Country {

    private final String name;
    private Player owner;
    private final int value;
    private Integer troops;
    private CountryView view;
    private boolean selected;
    private final Color defaultColor;
    private final Game game;
    private final ArrayList<Country> adjacentCountries;
    private final double layoutY;
    private final double layoutX;
    private String imageURI;

    public Country(String name, int value, int index, Game game, double Yoffset, double Xoffset) {
        this.name = name;
        this.defaultColor = Color.BEIGE;
        this.value = value;
        this.selected = false;
        this.game = game;
        this.troops = 0;
        this.layoutY = Yoffset;
        this.layoutX = Xoffset;
        this.game.addCountry(this);
        this.adjacentCountries = new ArrayList<>();
    }

    /**
     * Returns the name of the country.
     *
     * @return Name of the country.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the URI of the image used for the country view.
     *
     * @param uri
     */
    public void setImageURI(String uri) {
        this.imageURI = uri;
    }

    /**
     * Returns the URI of the image representing the country.
     *
     * @return
     */
    public String getImageURI() {
        return this.imageURI;
    }

    /**
     * Sets the owner of the country.
     *
     * @param newOwner
     */
    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }

    /**
     * Returns the Player who controls the country.
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Returns false if the country does not currently have an owner.
     *
     * @return Whether the country has an owner or not.
     */
    public boolean hasOwner() {
        return this.owner != null;
    }

    /**
     * Sets the troops present in the country.
     *
     * @param number The number of troops to set in the country.
     */
    public void setTroops(int number) {
        this.troops = number;
    }

    /**
     * Returns the number of troops currently in the country.
     *
     * @return Number of troops in the country
     */
    public int getTroops() {
        return this.troops;
    }

    /**
     * Returns the country's troop value, i.e. how many troops the country
     * brings to the owner during deployment.
     *
     * @return Number of troops the owner receives during deployment for owning
     * this country.
     */
    public int getTroopValue() {
        return this.value;
    }

    /**
     * Sets the current country as the selected country.
     */
    public void select() {
        this.selected = true;
        this.game.selectCountry(this);
    }

    /**
     * Reduces the number of troops in the country by one. Returns false of the
     * number falls to zero.
     *
     * @return Whether troops remain in the country.
     */
    public boolean reduceTroopsByOne() {
        this.troops--;
        this.view.updateTroopDisplay();
        return this.troops > 1;
    }

    /**
     * Adds a country to this country's list of adjacent countries.
     *
     * @param c Neighboring country
     */
    public void addNeighbor(Country c) {
        this.adjacentCountries.add(c);
    }

    /**
     * Sorts click events to this country according to game phase.
     */
    public void handleClickEvent() {
        switch (game.getPhase()) {
            case "COUNTRYSELECTION":
                this.handleSelectionPhaseClick();
                break;
            case "DEPLOYMENT":
                this.handleDeploymentPhaseClick();
                break;
            case "ATTACK":
                this.handleAttackPhaseClick("");
                break;
            default:
                break;
        }
    }

    /**
     * Handles a selection phase click.
     */
    public void handleSelectionPhaseClick() {
        if (!this.hasOwner()) {
            this.view.setColor(game.currentPlayer.getColor());
            this.setOwner(game.currentPlayer);
            this.troops = 1;
            this.owner.addTotalTroops(1);
            this.view.updateTroopDisplay();
            boolean countriesLeft = this.game.removeCountryToSet();
            if (!countriesLeft) {
                game.startTroopDeployment();
            } else {
                game.cyclePlayer();
            }
        }
    }

    /**
     * Handles a deployment phase click.
     */
    public void handleDeploymentPhaseClick() {
        if (this.owner.equals(game.getCurrentPlayer())) {
            this.owner.removeOneTroop();
            this.troops++;
            this.view.updateTroopDisplay();
            this.game.reduceTotalNumberOfTroopsLeftToDeploy();
            this.game.nextDeploymentTurn();
        }
    }
    
    /**
     * Handles an attack phase click.
     * @param type Not in use. Supply an empty string.
     */
    public void handleAttackPhaseClick(String type) {
        if (this.selected) {
            this.deselect();
        } else {
            if (this.game.getSelectedCountry() != null) {
                if (this.owner.equals(this.game.getCurrentPlayer())) {
                    this.moveTroops();
                } else {
                    this.receiveAttack();
                }
            } else {
                if (this.owner.equals(this.game.getCurrentPlayer())) {
                    selectThisCountry();
                }
            }
        }
    }

    /**
     * Handles an attack towards this country.
     */
    public void receiveAttack() {
        Country c = game.getSelectedCountry();
        Random r = new Random();
        if ((r.nextInt(100) + 1) > (r.nextInt(100) + 1)) { // Rolling a die
            this.reduceTroopsByOne();
            if (this.troops == 0) {
                this.changeOwnership(c);
                if (!this.game.moreThanOnePlayerLeft()) {
                    this.game.endGame(this.getOwner());
                }
            }
        } else {
            c.reduceTroopsByOne();
            game.getCurrentPlayer().removeOneTroop();
            if (c.getTroops() == 1) {
                c.deselect();
            }
        }
        this.view.updateTroopDisplay();
    }

    private void changeOwnership(Country attacker) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        this.setOwner(this.game.getCurrentPlayer());
        this.troops = 1;
        attacker.reduceTroopsByOne();
        attacker.deselect();
    }

    private void moveTroops() {
        this.troops++;
        if (game.getSelectedCountry().reduceTroopsByOne()) {

        } else {
            game.getSelectedCountry().deselect();
        }
        this.view.updateTroopDisplay();
    }

    private void selectThisCountry() {
        if (this.troops > 1) {
            this.darken();
            this.select();
            for (Country c : this.adjacentCountries) {
                c.brighten();
            }
        }
    }

    /**
     * Deselects this country as the current country.
     */
    public void deselect() {
        this.selected = false;
        this.resetHue();
        this.game.removeSelectedCountry();
        for (Country c : this.adjacentCountries) {
            c.resetHue();
        }
        if (this.owner != null) {
            this.view.setColor(this.owner.getColor());
        } else {
            this.view.setColor(Color.GRAY);
        }
    }

    /**
     * Returns the default color of countries.
     * @return The default color.
     */
    public Color getDefaultColor() {
        return this.defaultColor;
    }

    /**
     * Sets a lighter color for the country display.
     */
    public void brighten() {
        this.view.setColor(this.owner.getColor().brighter().brighter());
    }

    /**
     * Sets the country display color to the original owner player's color.
     */
    public void resetHue() {
        this.view.setColor(this.owner.getColor());
    }

    /**
     * Sets a darker shade for this country's view.
     */
    public void darken() {
        this.view.setColor(this.owner.getColor().darker());
    }

    /**
     * Attaches a CountryView to this country.
     * @param cw CountryView to attack to this country.
     */
    public void setView(CountryView cw) {
        this.view = cw;
    }

    /**
     * Returns information on whether the country is selected.
     * @return Whether the country is the current selected country.
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Returns the CountryView associated to this country.
     * @return The CountryView object representing this country.
     */
    public CountryView getCountryView() {
        return this.view;
    }

    /**
     * Retrieves the X and Y layout values for this country (as they are information that
     * is currently attached to the country, not the view.
     * @return The layoutX and layoutY values for a country's view.
     */
    public Double[] getCoordinates() {
        return new Double[]{this.layoutY, this.layoutX};
    }

}
