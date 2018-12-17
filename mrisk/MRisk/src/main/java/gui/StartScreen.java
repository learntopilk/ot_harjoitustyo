package gui;

import game.Country;
import game.Game;
import game.Player;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private BackgroundImage bg;
    private String playerInTurn;
    private String turnPhase;
    private TextField phaseDisplay = new TextField();

    public StartScreen() {
        game = new Game();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bg = new BackgroundImage(new Image("/f1.png", screenHeight, screenWidth, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        this.stage = primaryStage;
        primaryStage.setTitle("M-RISK");

        Button startGame = new Button("Start Game");
        startGame.setOnAction((ActionEvent event) -> {
            goToGameScreen();
        });

        Button quitGame = new Button("Quit game");
        quitGame.setOnAction((ActionEvent event) -> {
            quit();
        });

        Button seeStats = new Button("Stats");
        seeStats.setOnAction((ActionEvent event) -> {
            goToStats();
        });

        GridPane grid = createStartScreenGrid();
        grid.add(startGame, 0, 0);
        grid.add(seeStats, 0, 1);
        grid.add(quitGame, 0, 2);

        Scene startScene = new Scene(grid, 900, 700);
        this.start = startScene;

        p = startScene;
        primaryStage.setScene(p);
        primaryStage.show();

        game.start();
        update();
    }

    private GridPane createStartScreenGrid() {
        GridPane grid = new GridPane();
        grid.setBackground(new Background(bg));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        return grid;
    }

    private void goToStartScreen() {
        stage.setScene(start);
    }

    public void update() {
        this.playerInTurn = game.getCurrentPlayer().getName();
        this.updateTurnDisplay();
    }

    private void goToStats() {
        GridPane statGrid = new GridPane();

        Button returnBtn = new Button();
        returnBtn.setText("Back to Main");
        returnBtn.setOnAction((ActionEvent event) -> {
            goToStartScreen();
        });

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
        if (gameScene == null) {
            gameScene = createGameScene();
            gameScene.setOnMouseReleased((MouseEvent mouseEvent) -> {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        update();
                    }
                }, 250);
            });
        }
        stage.setScene(gameScene);
    }

    /**
     * Updates the turn display to indicate whose turn it is. NOT WORKING YET
     */
    public void updateTurnDisplay() {
        phaseDisplay.setText(playerInTurn);
        Player cur = this.game.getCurrentPlayer();
        if (cur == null) {
            phaseDisplay.setStyle("-fx-control-inner-background: #" + Color.GRAY.toString().substring(2));
        } else {
            phaseDisplay.setStyle("-fx-control-inner-background: #" + this.game.getCurrentPlayer().getColor().toString().substring(2));
        }
        //phaseDisplay.setText(turnPhase);
    }

    private Scene createGameScene() {
        Pane pane = new Pane();
        pane.setBackground(new Background(bg));

        Button menuBtn = menuButton();
        pane.getChildren().add(menuBtn);

        updateTurnDisplay();
        phaseDisplay.setLayoutX(screenWidth);
        phaseDisplay.setLayoutY(0);
        pane.getChildren().add(phaseDisplay);

        this.initializeCountries(pane);

        return new Scene(pane, 900, 700);
    }

    private Button menuButton() {
        Button b = new Button();
        b.setText("Return to Main Screen");
        b.setOnAction((ActionEvent event) -> {
            goToStartScreen();
        });
        return b;
    }

    private void initializeCountries(Pane p) {

        for (Country c : this.game.getCountries()) {
            Double[] coordinates = c.getCoordinates();
            CountryView cw = new CountryView(c, c.getImageURI(), this.game, coordinates[0], coordinates[1]);
            p.getChildren().add(cw.getImageView());
            p.getChildren().add(cw.getTextDisplay());
        }
    }
}
