/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author joonas
 */
public class Country {
    
    private String name;
    private Player owner;
    private int value;
    private Integer troops;
    
    public Country(String name, int value) {
        this.name = name;
        this.value = value;
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
    
    public int getTroops() {
        return this.troops;
    }
    
    
            
    
}
