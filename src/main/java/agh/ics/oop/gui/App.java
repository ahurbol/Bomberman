package agh.ics.oop.gui;

import agh.ics.oop.Direction;
import agh.ics.oop.GameMap;
import agh.ics.oop.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application {
    private GameMap gameMap;
    private static Stage stage;
    private Scene scene;
    private Player player1 = new Player();
    private Player player2 = new Player();


    public static void main(String[] args) {
        launch();
    }

    @FXML
    protected void startGame() {
        gameMap = new GameMap();
        gameMap.putPlayers(player1, player2);

        VBox vBox = new VBox(new Text(""));
        vBox.setSpacing(10);
        HBox boards = new HBox(vBox);
        boards.setSpacing(200);
        boards.setAlignment(Pos.CENTER);

        VBox buttonsAndBoards = new VBox(boards);
        ScrollPane wholeWindow = new ScrollPane(buttonsAndBoards);
        wholeWindow.setFitToWidth(true);
        wholeWindow.setFitToHeight(true);
        Grid grid = new Grid(gameMap);
        grid.update();
        vBox.getChildren().add(grid.getGrid());
        this.scene = new Scene(wholeWindow);
        this.scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W -> gameMap.tryMove(player1, Direction.UP);
                case A -> gameMap.tryMove(player1, Direction.LEFT);
                case S -> gameMap.tryMove(player1, Direction.DOWN);
                case D -> gameMap.tryMove(player1, Direction.RIGHT);
                case UP -> gameMap.tryMove(player2, Direction.UP);
                case LEFT -> gameMap.tryMove(player2, Direction.LEFT);
                case DOWN -> gameMap.tryMove(player2, Direction.DOWN);
                case RIGHT -> gameMap.tryMove(player2, Direction.RIGHT);
                case Z -> player1.tryPutBomb();
                case M -> player2.tryPutBomb();
            }
            grid.update();
        });
        stage.setScene(this.scene);
        stage.setResizable(true);
        stage.setMaximized(true);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("enter.fxml"));
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Bomberman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
