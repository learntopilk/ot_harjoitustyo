/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.Game;
//import java.awt.Image;
import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

/**
 *
 * @author joonas
 */
public class StartScreen extends Application {

    private final int HEIGHT = 768;
    private final int WIDTH = 699;
    private Scene p;
    private Stage stage;
    private Scene start;
    private Scene gameScene;
    private Game game;
    BackgroundImage bg;

    public StartScreen() {

    }

    public void begin() {
        //
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bg = new BackgroundImage(new Image("/f1.png", HEIGHT, WIDTH, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

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

        //StackPane root = new StackPane();
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
        //root.getChildren().add(btn);
        //root.getChildren().add(startGame);
        //root.autosize();
        //primaryStage.setScene(new Scene(root, 600, 800));

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
        //ImageView img = new ImageView();
        ImageView florida = new ImageView("/florida.png");
        //Image florida = new Image("/florida.png", 32, 32, false, false);
        florida.setPickOnBounds(false);
        florida.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("clicked Florida!");
        });

        pane.getChildren().add(florida);
        florida.setLayoutX(408d);
        florida.setLayoutY(453d);

        // CREATE AND ADD COUNTRIES
        return new Scene(pane, 900, 700);
    }
}
