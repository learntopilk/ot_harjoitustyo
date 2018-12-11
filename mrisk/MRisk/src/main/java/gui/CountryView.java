package gui;

import game.Country;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author joonas
 */
public class CountryView {

    private Country country;
    private ImageView i;
    private Color color;
    Game game;
    private TextField troopNumberDisplay;

    public CountryView(Country country) {
        this.country = country;
    }

    public CountryView(Country country, String uri, Game g, double layoutY, double layoutX) {
        this.country = country;
        this.game = g;

        Image im = new Image(uri);
        ImageView countryView = new ImageView(im);
        this.i = countryView;

        this.setColor(this.country.getDefaultColor());
        this.country.setView(this);
        
        countryView.setPickOnBounds(false);
        countryView.setLayoutX(layoutX);
        countryView.setLayoutY(layoutY);
        
        this.addTroopDisplay();
        this.updateTroopDisplay();
        
        countryView.setOnMouseClicked((MouseEvent e) -> {
            this.country.handleClickEvent();
        });

    }

    private void addTroopDisplay() {
        troopNumberDisplay = new TextField();
        troopNumberDisplay.setLayoutX(this.i.getLayoutX() + this.i.getImage().getWidth()/2-30);
        troopNumberDisplay.setLayoutY(this.i.getLayoutY() + this.i.getImage().getHeight()/2-30);
        troopNumberDisplay.setEditable(false);
        troopNumberDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        troopNumberDisplay.setAlignment(Pos.CENTER);
        troopNumberDisplay.setStyle(
                "-fx-control-inner-background: black;"
                + "-fx-border-radius: 20 20 20 20;"
                + "-fx-background-radius: 20 20 20 20;"
                + "-fx-text-fill: white;");
        troopNumberDisplay.setPrefWidth(80d);
        troopNumberDisplay.setPrefHeight(40d);
    }

    public Country getCountry() {
        return this.country;
    }

    public ImageView getImageView() {
        return this.i;
    }

    public void updateTroopDisplay() {
        troopNumberDisplay.setText(Integer.toString(this.country.getTroops()));
    }

    public TextField getTextDisplay() {
        return this.troopNumberDisplay;
    }

    /**
     * Sets the base color of the image view. For use when changing ownership of
     * a country.
     *
     * @param Color color The base color for the country, for use when changing
     * shades e.g. when the country is selected.
     * @return void
     */
    public void setColor(Color color) {
        this.color = color;
        this.updateColorView();
    }

    private void updateColorView() {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, this.color));
        this.i.setEffect(lighting);
        System.out.println("updating color of " + this.country.getName() + " to " + this.color.toString());
    }

    /**
     * Retrieves the current color of the country.
     *
     * @return Color color The current color of the country.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Darkens the shade of the country's current color.
     */
    public void darken() {
        this.setColor(this.color.darker());
        updateColorView();
        System.out.println("darkenShade");
    }

    /**
     * Attempts to reset the shade of the country's current color.
     */
    public void resetShade() {
        this.setColor(Color.GREEN);
        updateColorView();
    }

    /**
     * Lightens the shade of the country's current color.
     */
    public void lighten() {
        this.setColor(this.color.brighter());
        updateColorView();
    }
}
