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
    private Integer num;
    private Color color;
    private int troopsToSet;
    private int totalTroops;
    
    public Player(int num, String name) {
        this.num = num;
        this.name = name;
        this.troopsToSet = 0;
        this.color = Color.BLUE;
        this.totalTroops = troopsToSet;
    }
    
    public Player(int num) {
        this.num = num;
        this.name = "Player " + num;
        this.troopsToSet = 0;
    }
    
    public Player() {
        this.name = "Player";
        this.troopsToSet = 0;
    }
    
    public Player(Color color) {
        this.troopsToSet = 0;
        this.color = color;
        this.name = "Player " + this.color.toString();
    }
    
    public int getNum() {
        return this.num;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getTotalTroops() {
        return this.totalTroops;
    }
    
    public void addTotalTroops(int num) {
        this.totalTroops += num;
        System.out.println("Player " + this.name + " has " + this.totalTroops + " troops.");
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
    
    public Color getColor() {
        return this.color;
    }
    
}
