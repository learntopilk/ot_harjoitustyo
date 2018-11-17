/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author joonas
 */
public class StartScreen extends Application{
    
    
    public StartScreen () {
        
    }
    
    public void begin() {
        //
    }
    
    public static void main (String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("M-RISK");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Starting game...");
            }
        });
        
        Button quitGame = new Button();
        quitGame.setText("Quit game");
        quitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Quitting game...");
                quit();
            }
        });
        
        Button seeStats = new Button();
        seeStats.setText("Statistics");
        seeStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Opening statistics...");
            }
        });
        
        StackPane root = new StackPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        
        grid.add(startGame, 0, 0);
        grid.add(btn, 0, 1);
        grid.add(seeStats, 0, 2);
        grid.add(quitGame, 0, 3);
        
        Scene startScene = new Scene(grid, 700, 500);
        
        //root.getChildren().add(btn);
        //root.getChildren().add(startGame);
        
        //root.autosize();
        
        //primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
    
    private void goToGameScreen() {
        
    }
    
    private void goToStartScreen() {
        
    }
    
    private void goToStats() {
        
    }
    
    private void quit() {
        // TODO: kutsu tallennusta?
        System.exit(0);
    }
}
