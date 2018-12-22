/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class PlayerDisplay {

    private TextField p1Display;
    private TextField p2Display;
    private TextField phaseDisplay;
    private TextField turnDisplay;

    public PlayerDisplay() {
        p1Display = new TextField("p1");
        p1Display.setMinHeight(40d);
        p1Display.setMinWidth(200d);
        p1Display.setLayoutY(150);
        p1Display.setDisable(true);
        p1Display.setStyle("-fx-control-inner-background: #" + Color.GREEN.toString().substring(2));

        p2Display = new TextField("p2");
        p2Display.setMinHeight(40d);
        p2Display.setMinWidth(200d);
        p2Display.setLayoutY(200);
        p2Display.setDisable(true);
        p2Display.setStyle("-fx-control-inner-background: #" + Color.PURPLE.toString().substring(2));

        turnDisplay = new TextField("");
        turnDisplay.setMinHeight(40d);
        turnDisplay.setMinWidth(200d);
        turnDisplay.setLayoutY(250);
        turnDisplay.setDisable(true);

        phaseDisplay = new TextField("PHASE");
        phaseDisplay.setMinHeight(40d);
        phaseDisplay.setMinWidth(200d);
        phaseDisplay.setLayoutY(300);
        phaseDisplay.setDisable(true);
    }

    public TextField getTurnDisplay() {
        return this.turnDisplay;
    }

    public TextField getPhaseDisplay() {
        return this.phaseDisplay;
    }

    public TextField getPlayer1Display() {
        return this.p1Display;
    }

    public TextField getPlayer2Display() {
        return this.p2Display;
    }

    public void updatePhase(String phase) {
        System.out.println("updated phase");
        this.phaseDisplay.setText(phase);
    }

    public void setTurn(String playerName, Color c) {
        this.turnDisplay.setText(playerName);
        this.turnDisplay.setStyle("-fx-control-inner-background: #" + c.toString().substring(2));
    }

    public void updatePlayer1TroopsToSet(int troops) {
        p1Display.setText("Player 1: " + troops + " units to set");
    }

    public void updatePlayer2TroopsToSet(int troops) {
        p2Display.setText("Player 2: " + troops + " units to set");
    }

    public List<TextField> getAll() {
        List<TextField> l = new ArrayList<>();
        l.add(p1Display);
        l.add(p2Display);
        l.add(turnDisplay);
        l.add(phaseDisplay);

        return l;
    }
}
