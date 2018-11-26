/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.scene.image.ImageView;

/**
 *
 * @author joonas
 */
public class Country {
    
    private String name;
    private Player owner;
    private int value;
    private Integer troops;
    public ImageView img;
    private String ImageURI;
    private int index;
    private boolean selected;
    
    public Country(String name, String ImageURI, int value, int index) {
        this.name = name;
        this.value = value;
        this.index = index;
        this.selected = false;
        //img = new ImageView(ImageURI);
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
    
    public String getImageURI() {
        return this.ImageURI;
    }
    
    public int getTroops() {
        return this.troops;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void select() {
        this.selected = true;
        //OTHER THINGS
    }
    
    public void deselect() {
        this.selected = false;
        // Other things
    }
    
    
            
    
}
