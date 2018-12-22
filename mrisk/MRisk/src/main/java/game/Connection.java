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
    
    private Country start;
    private String end;
    
    /**
     * A connection between two countries. As the country at the end may not have been generated,
     * the name of the end will be given instead.
     * @param start The country from which the connection starts
     * @param end Name of the country to which the connection ends.
     */
    public Connection(Country start, String end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * The country from which this connection starts
     * @return Country at the beginning of the connection
     */
    public Country getStart() {
        return this.start;
    }
    
    /**
     * 
     * @return name of the country this connections ends with.
     */
    public String getEnd() {
        return this.end;
    }
}
