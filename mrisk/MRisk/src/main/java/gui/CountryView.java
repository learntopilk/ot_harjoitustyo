package gui;

import game.Country;
import game.Game;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class CountryView {

    private Country country;
    public ImageView i;
    private Color color;
    Game game;

    public CountryView(Country country) {
        this.country = country;
    }

    public CountryView(Country country, String uri, Game g, double layoutY, double layoutX) {
        this.country = country;
        this.game = g;
        this.color = Color.BISQUE;

        Image im = new Image(uri);
        ImageView countryView = new ImageView(im);
        this.i = countryView;
        this.country.setView(this);
        countryView.setPickOnBounds(false);
        countryView.setLayoutX(layoutX);
        countryView.setLayoutY(layoutY);
        countryView.setOnMouseClicked((MouseEvent e) -> {
            this.country.handleClickEvent();
            //System.out.println("clicked " + this.country.getName());
            // Jos tämä valittu, nollaa
            /*if (this.country.isSelected()) {
                this.country.deselect();
            } else {
                this.game.selectCountry(this.country.getIndex());
                game.advanceRound();
                //this.country.select();
                System.out.println("Selected: " + this.game.getSelectedCountry().getName());
            }*/
        });

    }

    public Country getCountry() {
        return this.country;
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

    public void updateColorView() {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        //lighting.setLight(new Light.Distant(45, 45, game.getCurrentPlayer().getColor()));
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
