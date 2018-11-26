package gui;

import game.Country;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class CountryView {

    private Country country;
    public ImageView i;
    private Color color;

    public CountryView(Country country) {
        this.country = country;
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

    }

    /**
     * Lightens the shade of the country's current color.
     */
    public void lighten() {

    }
}
