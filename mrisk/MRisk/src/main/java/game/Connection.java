/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 * A helper class for storing connections between countries while the countries
 * are still in the process of being initialized.
 * @author joonas
 */
public class Connection {
    
    private String start;
    private String end;
    
    public Connection(String start, String end) {
        this.start = start;
        this.end = end;
    }
    
    public String getStart() {
        return this.start;
    }
    
    public String getEnd() {
        return this.end;
    }
}
