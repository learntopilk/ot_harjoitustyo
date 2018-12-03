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
import utils.CountryCreator;

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
    private String endButtonText;
    
    public static void main(String[] args) {
        
    }
    
    public Game() {
        //this.players = new HashMap<>();
        this.countries = new ArrayList<>();
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
    
    public void cyclePlayer() {
        
    }
    
    public void initializeCountries() {
        //countries = CountryCreator.createCountries(this);
        //countries = new ArrayList<>();
    }
    
    public void addCountry(Country c) {
        this.countries.add(c);
    }
    
    public List<Country> getCountries() {
        return this.countries;
    }
    
    public Country getCountry(int index) {
        return this.countries.get(index);
    }
    
    public void selectCountry(int index) {
        Country oldCountry = this.selectedCountry;
        if (oldCountry == null) {
            System.out.println("nobody selected");
        } else {
            System.out.println(oldCountry.getName() + "!!!");
            oldCountry.deselect();
        }

        this.selectedCountry = getCountry(index);
        this.selectedCountry.select();
        
        
        System.out.println(this.selectedCountry.getName());
        // UPDATE COLORS
    }
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
