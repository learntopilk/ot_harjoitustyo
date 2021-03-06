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
import javafx.scene.control.Tooltip;

/**
 *
 * @author joonas
 */
public class CountryView {

    private final Country country;
    private ImageView i;
    private Color color;
    private Game game;
    private TextField troopNumberDisplay;

    public CountryView(Country country) {
        this.country = country;
    }

    public CountryView(Country country, String uri, Game g, double layoutY, double layoutX) {
        this.country = country;
        this.game = g;

        Image im = new Image("resources"+uri);
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
            this.game.updateTroopDisplays();
        });
        
        Tooltip t = new Tooltip(this.country.getName() + ". Value: " + this.country.getTroopValue());
        Tooltip.install(i, t);

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
        troopNumberDisplay.setMouseTransparent(true);
    }

    /**
     * 
     * @return The country this CountryView represents
     */
    public Country getCountry() {
        return this.country;
    }

    /**
     * @return The ImageView containing the image representing this view.
     */
    public ImageView getImageView() {
        return this.i;
    }

    /**
     * Updates the troop display for this view.
     */
    public void updateTroopDisplay() {
        troopNumberDisplay.setText(Integer.toString(this.country.getTroops()));
    }

    /**
     * 
     * @return The troop number display attached to this country.
     */
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

    /**
     * Updates the color for this view.
     */
    private void updateColorView() {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, this.color));
        this.i.setEffect(lighting);
    }

    /**
     * Retrieves the current color of the country.
     *
     * @return Color color The current color of the country.
     */
    public Color getColor() {
        return this.color;
    }
}
