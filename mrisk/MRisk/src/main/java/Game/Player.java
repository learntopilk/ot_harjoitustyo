/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Player {
    
    private String name;
    private Integer num;
    private Color color;
    private int troopsToSet;
    
    public Player(int num, String name) {
        this.num = num;
        this.name = name;
        this.troopsToSet = 10;
    }
    
    public Player(int num) {
        this.num = num;
        this.name = "Player " + num;
        this.troopsToSet = 10;
    }
    
    public Player() {
        this.name = "Player";
        this.troopsToSet = 10;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addTroopsToSet(int num) {
        this.troopsToSet += num;
    }
    
    public Integer getTroopsToSet() {
        return this.troopsToSet;
    }
    
    public void removeOneTroop() {
        this.troopsToSet--;
    }
    
}
