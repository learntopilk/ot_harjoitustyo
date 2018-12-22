/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Player {
    
    private String name;
    private Color color;
    private int troopsToSet;
    private int totalTroops;
    
    /**
     * Creates a dummy player object.
     */
    public Player() {
        this.name = "Player";
        this.color = Color.BLUE;
        this.troopsToSet = 0;
    }
    
    /**
     * Creates a new player with the specified color.
     * @param color 
     */
    public Player(Color color) {
        this.troopsToSet = 0;
        this.color = color;
        this.name = "Player " + this.color.toString();
    }
    
    /**
     * Returns the player's name
     * @return Player name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the total number of troops the player possesses.
     * @return Total troops 
     */
    public int getTotalTroops() {
        return this.totalTroops;
    }
    
    /**
     * Adds the specified number of troops to the player's troop count. Also adds
     * the troops to the count of troops the player must deploy.
     * @param num Number of troops to add to total count
     */
    public void addTotalTroops(int num) {
        this.totalTroops += num;
        this.troopsToSet += num;
    }
    
    /**
     * Add troops to the player's pile of troops that they can still set.
     * @param num Number of troops to add
     */
    public void addTroopsToSet(int num) {
        this.troopsToSet += num;
    }
    
    /**
     * Returns the number of troops the player still has to set.
     * @return Number of troops left to deploy
     */
    public int getTroopsToSet() {
        return this.troopsToSet;
    }
    
    /**
     * Reduces the number of troops in the troops to set pile by one.
     */
    public boolean removeOneTroop() {
        if (this.troopsToSet > 0) {
            this.troopsToSet--;
        }
        return this.troopsToSet > 0;
    }
    
    /**
     * Returns the player's color.
     * @return The player's color.
     */
    public Color getColor() {
        return this.color;
    }
    
}
