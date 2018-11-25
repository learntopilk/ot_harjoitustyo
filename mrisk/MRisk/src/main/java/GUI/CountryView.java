/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.Country;
import javafx.scene.image.ImageView;

/**
 *
 * @author joonas
 */
public class CountryView {
    private Country country;
    public ImageView i;
    
    public CountryView(Country country) {
        this.country = country;
    }
 }
