package agh.ics.oop.gui;

import agh.ics.oop.GameMap;
import agh.ics.oop.Vector2d;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Grid {
    private final GridPane grid = new GridPane();
    private final int width;
    private final int height;
    private final GameMap gameMap;

    public Grid(GameMap gameMap) {
        this.gameMap = gameMap;
        this.width = gameMap.getWidth();
        this.height = gameMap.getHeight();
    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void update() {
        Platform.runLater(() -> {
            grid.getChildren().clear();
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            grid.getRowConstraints().add(new RowConstraints(40));
            grid.setGridLinesVisible(true);

            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    Vector2d position = new Vector2d(x, y);
                    if (gameMap.hasSthAt(position)) {
                        Box box;
                    } else {
                        Label labell = new Label();
                        labell.setMinWidth(40);
                        labell.setMinHeight(40);
                        labell.setStyle("-fx-background-color: #7592a1;");
                        grid.add(labell, x, y, 1, 1);
                    }

                }
            }

        });
    }

}
