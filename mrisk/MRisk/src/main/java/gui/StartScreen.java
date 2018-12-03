/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game.Country;
import game.Game;
import game.Player;
import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author joonas
 */
public class StartScreen extends Application {

    private final int screenHeight = 768;
    private final int screenWidth = 699;
    private Scene p;
    private Stage stage;
    private Scene start;
    private Scene gameScene;
    private Game game;
    BackgroundImage bg;

    public StartScreen() {
        game = new Game();
    }

    public void begin() {
        //
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bg = new BackgroundImage(new Image("/f1.png", screenHeight, screenWidth, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        this.stage = primaryStage;
        primaryStage.setTitle("M-RISK");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                btn.setText("Hello World said");
            }
        });
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToGameScreen();
            }
        });

        Button quitGame = new Button();
        quitGame.setText("Quit game");
        quitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Quitting game...");
                quit();
            }
        });

        Button seeStats = new Button();
        seeStats.setText("Statistics");
        seeStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening statistics...");
                goToStats();
            }
        });

        GridPane grid = new GridPane();
        grid.setBackground(new Background(bg));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(startGame, 0, 0);
        grid.add(btn, 0, 1);
        grid.add(seeStats, 0, 2);
        grid.add(quitGame, 0, 3);

        Scene startScene = new Scene(grid, 900, 700);
        this.start = startScene;

        p = startScene;
        primaryStage.setScene(p);
        primaryStage.show();
    }

    private void goToStartScreen() {
        stage.setScene(start);
    }

    private void goToStats() {
        GridPane statGrid = new GridPane();

        Button returnBtn = new Button();
        returnBtn.setText("Back to Main");
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToStartScreen();
            }
        });
        // Should this be in its own class?
        statGrid.setAlignment(Pos.CENTER);
        statGrid.add(returnBtn, 0, 0);
        Scene statScene = new Scene(statGrid, 900, 700);
        System.out.println("1");
        stage.setScene(statScene);
    }

    private void quit() {
        // TODO: see if saving is necessary here?
        System.exit(0);
    }

    private void goToGameScreen() {
        System.out.println("Starting game...");
        if (gameScene == null) {
            gameScene = createGameScene();
        }
        stage.setScene(createGameScene());
    }

    private Scene createGameScene() {
        Pane pane = new Pane();
        pane.setBackground(new Background(bg));

        Button menuBtn = new Button();
        menuBtn.setText("Return to Main Screen");
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Returnint to menu screen from main screen");
                goToStartScreen();
            }
        });

        pane.getChildren().add(menuBtn);
        
        CountryView florida = new CountryView(new Country("Florida", 4, 0), "/florida.png", this.game, 453d, 408d);
        pane.getChildren().add(florida.i);
        this.game.addCountry(florida.getCountry());
        //florida.setLayoutX(408d);
        //florida.setLayoutY(453d);
        /*Image fl = new Image("/florida.png");
        ImageView florida = new ImageView(fl);
        florida.setPickOnBounds(false);
        florida.setOnMouseClicked((MouseEvent e) -> {
            Lighting lighting = new Lighting();
            lighting.setDiffuseConstant(1.0);
            lighting.setSpecularConstant(0.0);
            lighting.setSpecularExponent(0.0);
            lighting.setSurfaceScale(0.0);
            lighting.setLight(new Light.Distant(45, 45, game.getCurrentPlayer().getColor()));
            System.out.println("clicked Florida!");
            
            florida.setEffect(lighting);
        });

        Image ge = new Image("/georgia.png");
        ImageView georgia = new ImageView(ge);
        georgia.setPickOnBounds(false);
        georgia.setOnMouseClicked((MouseEvent e) -> {
            Lighting lighting = new Lighting();
            lighting.setDiffuseConstant(1.0);
            lighting.setSpecularConstant(0.0);
            lighting.setSpecularExponent(0.0);
            lighting.setSurfaceScale(0.0);
            lighting.setLight(new Light.Distant(45, 45, Color.PURPLE));
            System.out.println("clicked Georgia!");
            georgia.setEffect(lighting);
        });*/

        //pane.getChildren().add(georgia);
        //georgia.setLayoutX(460d);
        //georgia.setLayoutY(289d);
        

        // CREATE AND ADD COUNTRIES
        return new Scene(pane, 900, 700);
    }
}
