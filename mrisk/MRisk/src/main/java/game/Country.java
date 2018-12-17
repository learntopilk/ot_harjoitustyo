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

    public String getName() {
        return this.name;
    }

    public void setImageURI(String uri) {
        this.imageURI = uri;
    }

    public String getImageURI() {
        return this.imageURI;
    }

    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public void setTroops(int number) {
        this.troops = number;
    }

    public int getTroops() {
        return this.troops;
    }

    public int getTroopValue() {
        return this.value;
    }

    public void select() {
        this.selected = true;
        this.game.selectCountry(this);
    }
    
    public boolean reduceTroopsByOne() {
        this.troops--;
        this.view.updateTroopDisplay();
        return this.troops > 1;
    }

    public void addNeighbor(Country c) {
        this.adjacentCountries.add(c);
    }

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
                System.out.println("Phase: " + game.getPhase());
        }
    }

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

    public void handleDeploymentPhaseClick() {
        if (this.owner.equals(game.getCurrentPlayer())) {
            this.owner.removeOneTroop();
            this.troops++;
            this.view.updateTroopDisplay();
            this.game.reduceTotalNumberOfTroopsLeftToDeploy();
            this.game.nextDeploymentTurn();
        }
    }

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
    
    public void receiveAttack () {
        Country c = game.getSelectedCountry();
        Random r = new Random();
        if ((r.nextInt(100)+1) > (r.nextInt(100)+1)) {
            this.reduceTroopsByOne();
            if (this.troops == 0) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                this.setOwner(this.game.getCurrentPlayer());
                this.troops = 1;
                c.reduceTroopsByOne();
                c.deselect();
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

    public Color getDefaultColor() {
        return this.defaultColor;
    }

    public void brighten() {
        this.view.setColor(this.owner.getColor().brighter().brighter());
    }

    public void resetHue() {
        this.view.setColor(this.owner.getColor());
    }

    public void darken() {
        this.view.setColor(this.owner.getColor().darker());
    }

    public void setView(CountryView cw) {
        this.view = cw;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public CountryView getCountryView() {
        return this.view;
    }

    public Double[] getCoordinates() {
        return new Double[]{this.layoutY, this.layoutX};
    }

}
