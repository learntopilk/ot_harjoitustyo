/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import GUI.StartScreen;
import java.util.HashMap;

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
    int round;
    
    public static void main(String[] args) {
        
    }
    
    public Game() {
        //this.players = new HashMap<>();
        players = 2;
        
        p1 = new Player();
        p2 = new Player();
        
        round = 1;
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
}
