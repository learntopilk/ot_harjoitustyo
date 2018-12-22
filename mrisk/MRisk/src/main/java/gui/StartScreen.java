package gui;

import game.Country;
import game.Game;
import java.io.PrintStream;
import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    public PlayerDisplay pd;
    private Button endTurn;

    public StartScreen() {
        game = new Game();
        this.pd = new PlayerDisplay();
        game.attachView(this);
    }

    public static void main(String[] args) {
        try {
            System.setErr(new PrintStream("/dev/null"));
        } catch (Exception e) {
            //
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bg = new BackgroundImage(new Image("/f1.png", screenHeight, screenWidth, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        this.stage = primaryStage;
        primaryStage.setTitle("M-RISK");
        primaryStage.setResizable(false);

        Button startGame = new Button("Start Game");
        startGame.setOnAction((ActionEvent event) -> {
            goToGameScreen();
        });

        Button quitGame = new Button("Quit game");
        quitGame.setOnAction((ActionEvent event) -> {
            quit();
        });

        Button seeStats = new Button("How to Play");
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

    /**
     * Updates the visibility status of the "end turn" button, necessary for ending attack turns.
     */
    public void update() {
        if (this.endTurn != null) {
            if (this.game.getPhase().equals("ATTACK")) {
                this.endTurn.setVisible(true);
            } else {
                this.endTurn.setVisible(false);
            }
        }

    }

    private void goToStats() {
        GridPane statGrid = new GridPane();

        Button returnBtn = new Button();
        returnBtn.setText("Back to Main");
        returnBtn.setOnAction((ActionEvent event) -> {
            goToStartScreen();
        });

        statGrid.setAlignment(Pos.CENTER);
        statGrid.add(returnBtn, 0, 1);
        
        TextArea t = new TextArea();
        t.setEditable(false);
        t.setMouseTransparent(true);
        t.setMinHeight(600d);
        t.setMinWidth(700d);
        t.setText("MRisk is a Risk clone. "
                + "\n \nThe game has three phases: "
                + "\n 1. Country selection"
                + "\n Players pick countries. Green begins."
                + "\n\n"
                + "\n 2. Troop deployment:"
                + "\n The number of troops each player has to deploy is calculated based on the states each player owns. "
                + "\n Green player starts deployment. The displays on the right side of the window"
                + " show how many \n troops each player must deploy."
                + "\n\n"
                + "\n 3. Attack phase."
                + "\n Players attack each other. Purple begins. Click on a country that has more than one troop, "
                + "\n and those around it will be highlighted. You can attack one of these countries, or move troops to"
                + "\n one of your countries."
                + "\n End your attack turn by clicking \"End turn\". (If you do not see the button, click somewhere on the "
                + "\n screen.)"
                + "\n \n"
                + "To start a new game, you need to restart the application.");
        
        statGrid.add(t, 0, 0);
        Scene statScene = new Scene(statGrid, 900, 700);
        stage.setScene(statScene);
    }

    private void quit() {
        System.exit(0);
    }

    private void goToGameScreen() {
        if (gameScene == null) {
            gameScene = createGameScene();
            gameScene.setOnMouseReleased((MouseEvent mouseEvent) -> {
                update();
            });
        }
        stage.setScene(gameScene);
    }

    private Scene createGameScene() {
        Pane pane = new Pane();
        this.pd = new PlayerDisplay();
        pd.updatePhase(this.game.getPhase());
        pane.setBackground(new Background(bg));

        Button menuBtn = menuButton();
        pane.getChildren().add(menuBtn);

        for (TextField t : pd.getAll()) {
            pane.getChildren().add(t);
        }

        this.endTurn = endTurnButton();
        pane.getChildren().add(endTurn);
        endTurn.setVisible(false);

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

    private Button endTurnButton() {
        Button b = new Button("End turn");;
        b.setLayoutY(30d);
        b.setOnAction((ActionEvent e) -> {
            this.game.advanceRound();
            if (this.game.getPhase().equals("ATTACK")) {
                b.setVisible(false);
            }
        });
        return b;
    }

    /**
     * Updates the phase display to help players know what the hell is going on.
     * @param phase A string value designating the current game phase.
     */
    public void updatePhaseDisplay(String phase) {
        this.pd.updatePhase(phase);
    }
}
