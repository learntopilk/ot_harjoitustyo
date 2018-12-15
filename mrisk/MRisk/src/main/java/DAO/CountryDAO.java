package DAO;

import game.Connection;
import game.Country;
import game.Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static javafx.scene.input.DataFormat.URL;

/**
 *
 * @author joonas
 */
public class CountryDAO {

    private ArrayDeque<Connection> connections;
    private ArrayList<Country> countries;
    private Game game;
    
    public CountryDAO(Game g) {
        System.out.println("Contsructor in countryDAO");
        connections = new ArrayDeque<>();
        countries = new ArrayList<>();
        this.game = g;
    }

    public List readCountries() {
        System.out.println("starting read operation...");
        List<Country> countries = new ArrayList<>();
        try {
            Scanner map = new Scanner(new FileReader(new File("src/main/resources/countries.csv")));
            while (map.hasNext()) {
                countries.add(scrapeCountry(map.next(), this.game));
            }
            connectCountries();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return countries;
    }

    private Country scrapeCountry(String rawData, Game g) {
        System.out.println(rawData);
        String[] data = rawData.split(";");
        String name = data[0].replaceAll("%20", " ");
        System.out.println("Name: " + name);
        Integer troopValue = Integer.parseInt(data[1]);
        Double offsetY = Double.parseDouble(data[3]);
        Double offsetX = Double.parseDouble(data[4]);
        Country c = new Country(name, troopValue, 0, g, offsetY, offsetX);
        c.setImageURI(data[2]);
        for (int i = 5; i < data.length; i++) {
            String targetCountryName = data[i].replaceAll("%20", " ");
            connections.addLast(new Connection(c, targetCountryName));
        }
        return c;
    }
    
    private Country findCountryByName(String name) {
        for (Country c : this.countries) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
    
    private void connectCountries() {
        while (!connections.isEmpty()) {
            Connection conn = connections.pollFirst();
            conn.getStart().addNeighbor(findCountryByName(conn.getEnd()));
        }
    }

}
