package gui;

import game.Country;
import game.Game;
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
        this.color = color;
        this.country = country;
        this.game = g;
        
        Image im = new Image(uri);
        ImageView countryView = new ImageView(im);
        this.i = countryView;
        this.country.setView(this);
        countryView.setPickOnBounds(false);
        countryView.setLayoutX(layoutX);
        countryView.setLayoutY(layoutY);
        countryView.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("clicked " + this.country.getName());
            // Jos tämä valittu, nollaa
            if (this.country.isSelected()) {
                System.out.println("already selected");
            } else {
                Lighting lighting = new Lighting();
                lighting.setDiffuseConstant(1.0);
                lighting.setSpecularConstant(0.0);
                lighting.setSpecularExponent(0.0);
                lighting.setSurfaceScale(0.0);
                lighting.setLight(new Light.Distant(45, 45, game.getCurrentPlayer().getColor()));
                countryView.setEffect(lighting);
                this.game.selectCountry(this.country.getIndex());
                //this.country.select();
            }
            
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
    
    public void darkenShade(CountryView cw, Color color) {
        
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(10, 10, this.color));
        System.out.println("clicked Florida!");
        
        i.setEffect(lighting);
    }
    
    public static void showColor() {
        
    }

    /**
     * Lightens the shade of the country's current color.
     */
    public void lighten() {
        
    }
}
