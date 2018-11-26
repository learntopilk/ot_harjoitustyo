/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gui.StartScreen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class Game {
    
    // Maps player names to player numbers
    //HashMap<String, Integer> players;
    int players;
    Player p1;
    Player p2;
    Player currentPlayer;
    int round;
    private List<Country> countries;
    private Country selectedCountry;
    
    public static void main(String[] args) {
        
    }
    
    public Game() {
        //this.players = new HashMap<>();
        players = 2;
        p1 = new Player(Color.GREEN);
        p2 = new Player(Color.PURPLE);
        currentPlayer = p1;
        
        round = 1;
        
        initializeCountries();
    }
    
    public void addPlayer(String name) {
        
    }
    
    public void attack(int attacker, int defender) {
        
    }
    
    public void start() {
        
    }
    
    public void endTurn() {
        round++;
        if (round > 2) {
            round = 1;
        }
    }
    
    public void advanceRound() {
        
    }
    
    public void initializeCountries() {
        countries = new ArrayList<>();
        countries.add(new Country("Florida", "/florida.png", 4, countries.size()));
        countries.add(new Country("Georgia", "/georgia.png", 2, countries.size()));
    }
    
    public List<Country> getCountries() {
        return this.countries;
    }
    
    public Country getCountry(int index) {
        return this.countries.get(index);
    }
    
    public void selectCountry(int index) {
        Country oldCountry = this.selectedCountry;
        oldCountry.deselect();
        getCountry(index).select();
        // UPDATE COLORS
    }
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
