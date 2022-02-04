package agh.ics.oop.gui;

import agh.ics.oop.GameMap;
import agh.ics.oop.Player;
import agh.ics.oop.Vector2d;
import javafx.application.Platform;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Grid {
    private final GridPane grid = new GridPane();
    private final int width;
    private final int height;
    private final GameMap gameMap;
    private final Box box;

    public Grid(GameMap gameMap, Player player1, Player player2) {
        this.gameMap = gameMap;
        this.width = gameMap.getWidth();
        this.height = gameMap.getHeight();
        box = new Box(player1, player2);
    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void update() {
        Platform.runLater(() -> {
            grid.getChildren().clear();
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().add(new ColumnConstraints(5));
            grid.getRowConstraints().add(new RowConstraints(5));

            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    grid.add(this.box.getBox(this.gameMap.objectAt(new Vector2d(x, y))), x + 1, y + 1);
                }
            }

        });
    }

}
