package game;

import gui.CountryView;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Country {

    private String name;
    private Player owner;
    private int value;
    private Integer troops;
    private CountryView view;
    private boolean selected;
    private Color defaultColor = Color.BEIGE;
    private Game game;

    public Country(String name, int value, int index, Game game) {
        this.name = name;
        this.defaultColor = Color.BEIGE;
        this.value = value;
        this.selected = false;
        this.game = game;
        this.troops = 0;
        this.game.addCountry(this);
    }

    public String getName() {
        return this.name;
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
                this.handleAttackPhaseClick("placeholder");
                break;
            default:
                System.out.println("Phase: " + game.getPhase());
                System.out.println("You're in deep shit, yo");
        }
    }

    public void handleSelectionPhaseClick() {
        if (this.hasOwner()) {
        } else {
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
            // Update view and cycle player + check if other player has troops to commit
        } else {
        }
    }

    public void handleAttackPhaseClick(String type) {

    }

    public void deselect() {
        this.selected = false;
        if (this.owner != null) {
            this.view.setColor(this.owner.getColor());
        } else {
            this.view.setColor(Color.BLUE);
        }
    }

    public Color getDefaultColor() {
        return this.defaultColor;
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

}
