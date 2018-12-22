/*
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * A collection of game information displays for aiding the players in situational awareness with game state, troop deployment and so on.
 * @author joonas
 */
public class PlayerDisplay {

    private TextField p1Display;
    private TextField p2Display;
    private TextField phaseDisplay;
    private TextField turnDisplay;

    /**
     * Creates a set of game info displays.
     */
    public PlayerDisplay() {

        phaseDisplay = new TextField("PHASE");
        phaseDisplay.setMinHeight(40d);
        phaseDisplay.setMinWidth(200d);
        phaseDisplay.setLayoutY(70);
        phaseDisplay.setEditable(false);
        phaseDisplay.setMouseTransparent(true);
        phaseDisplay.setFocusTraversable(false);

        p1Display = new TextField("p1");
        p1Display.setMinHeight(40d);
        p1Display.setMinWidth(200d);
        p1Display.setLayoutY(120);
        p1Display.setStyle("-fx-control-inner-background: #" + Color.GREEN.toString().substring(2));
        p1Display.setEditable(false);
        p1Display.setMouseTransparent(true);
        p1Display.setFocusTraversable(false);

        p2Display = new TextField("p2");
        p2Display.setMinHeight(40d);
        p2Display.setMinWidth(200d);
        p2Display.setLayoutY(170);
        p2Display.setStyle("-fx-control-inner-background: #" + Color.PURPLE.toString().substring(2));
        p2Display.setEditable(false);
        p2Display.setMouseTransparent(true);
        p2Display.setFocusTraversable(false);

        turnDisplay = new TextField("TURN DISPLAY");
        turnDisplay.setMinHeight(40d);
        turnDisplay.setMinWidth(200d);
        turnDisplay.setLayoutY(220);
        turnDisplay.setEditable(false);
        turnDisplay.setMouseTransparent(true);
        turnDisplay.setFocusTraversable(false);
    }

    /**
     * Returns the turn display.
     * @return Turn display TextField
     */
    public TextField getTurnDisplay() {
        return this.turnDisplay;
    }

    /**
     * Returns the phase display.
     * @return Phase display TextField
     */
    public TextField getPhaseDisplay() {
        return this.phaseDisplay;
    }

    /**
     * Returns the player display for player 1.
     * @return Player 1's display
     */
    public TextField getPlayer1Display() {
        return this.p1Display;
    }

    /**
     * Returns the player display for player 2.
     * @return Player 2's display
     */
    public TextField getPlayer2Display() {
        return this.p2Display;
    }

    /**
     * Updates the phase display to show the current game phase.
     * @param phase Phase string to display
     */
    public void updatePhase(String phase) {
        this.phaseDisplay.setText("Phase: " + phase);
    }

    /**
     * Sets the color of the turn display to correspond to that of the current player.
     * @param c Color of the current player
     */
    public void setTurn(Color c) {
        this.turnDisplay.setStyle("-fx-control-inner-background: #" + c.toString().substring(2));
    }

    /**
     * Updated the troop count display for player 1.
     * @param troops Number of troops to deploy
     */
    public void updatePlayer1TroopsToSet(int troops) {
        p1Display.setText("Player 1: " + troops + " units to set");
    }

    /**
     * Updates the troop count for player 2.
     * @param troops Number of troops to deploy
     */
    public void updatePlayer2TroopsToSet(int troops) {
        p2Display.setText("Player 2: " + troops + " units to set");
    }

    /**
     * Returns all the game information fields.
     * @return 
     */
    public List<TextField> getAll() {
        List<TextField> l = new ArrayList<>();
        l.add(p1Display);
        l.add(p2Display);
        l.add(turnDisplay);
        l.add(phaseDisplay);

        return l;
    }
}
