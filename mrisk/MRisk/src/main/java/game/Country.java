/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public CountryView view;
    private String imageUri;
    private int index;
    private boolean selected;
    private Color defaultColor = Color.BEIGE;
    private Game game;

    public Country(String name, int value, int index, Game game) {
        this.name = name;
        this.value = value;
        this.index = index;
        this.selected = false;
        this.game = game;
        this.troops = 1;
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

    public String getImageUri() {
        return this.imageUri;
    }

    public int getTroops() {
        return this.troops;
    }

    public int getTroopValue() {
        return this.value;
    }

    public int getIndex() {
        return this.index;
    }

    public void select() {
        this.selected = true;
        this.view.darken();
    }

    public void handleClickEvent() {
        switch (game.getPhase()) {
            case "COUNTRYSELECTION":
                System.out.println("Selecting ");

                if (this.hasOwner()) {
                    System.out.println(this.getName() + " cannot be selected, owner: " + this.owner.getName());
                } else {
                    this.view.setColor(game.currentPlayer.getColor());
                    this.setOwner(game.currentPlayer);
                    boolean countriesLeft = this.game.removeCountryToSet();
                    if (!countriesLeft) {
                        game.startTroopDeployment();
                    } else {
                        game.cyclePlayer();
                    }
                    
                }

                break;
            case "DEPLOyMENT":
                System.out.println("Deploying troops");
                
                break;
            case "ATTACK":
                System.out.println("ATTACK");
                break;
            default:
                System.out.println("You're in deep shit, yo");
        }
    }

    public void deselect() {
        this.selected = false;
        if (this.owner != null) {
            System.out.println("mös");
            this.view.setColor(this.owner.getColor());
        } else {
            System.out.println("PÖS");
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

}
