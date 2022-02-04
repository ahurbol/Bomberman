package agh.ics.oop.gui;

import agh.ics.oop.GameMap;
import agh.ics.oop.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Box extends Node {
    private final Map<Object, Image> images = new LinkedHashMap<>();
    private final Map<Pair<Player, Health>, Image> playersImages = new LinkedHashMap<>();
    private Image nothing;

    public Box(Player player1, Player player2) {
        getImages(player1, player2);
    }

    public HBox getBox(Object object) {
        HBox hBox = new HBox();
        ImageView imageView;
        if (object == null) {
            imageView = new ImageView(this.nothing);
        } else if (object instanceof Player){
            Player player = (Player) object;
            imageView = new ImageView(this.playersImages.get(new Pair<>(player, player.getHealth())));

        } else {
            imageView = new ImageView(this.images.get(object));
        }
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        hBox.getChildren().add(imageView);
        return hBox;
    }

    public void getImages(Player player1, Player player2) {
        try {
            this.images.put(new Bomb(), new Image(new FileInputStream("src/main/resources/bomb.png")));
            this.images.put(new Chest(), new Image(new FileInputStream("src/main/resources/barrel.png")));
            this.images.put(new Wall(), new Image(new FileInputStream("src/main/resources/hexes.png")));

            Pair<Player, Health> a = new Pair<>(player1, Health.ALL);
            Pair<Player, Health> b = new Pair<>(player1, Health.LESS);
            Pair<Player, Health> c = new Pair<>(player1, Health.LAST);
            Pair<Player, Health> d = new Pair<>(player1, Health.DEAD);
            Pair<Player, Health> e = new Pair<>(player2, Health.ALL);
            Pair<Player, Health> f = new Pair<>(player2, Health.LESS);
            Pair<Player, Health> g = new Pair<>(player2, Health.LAST);
            Pair<Player, Health> h = new Pair<>(player2, Health.DEAD);


            this.playersImages.put(a, new Image(new FileInputStream("src/main/resources/robot1.png")));
            this.playersImages.put(b, new Image(new FileInputStream("src/main/resources/robot2.png")));
            this.playersImages.put(c, new Image(new FileInputStream("src/main/resources/robot3.png")));
            this.playersImages.put(d, new Image(new FileInputStream("src/main/resources/robot4.png")));

            this.playersImages.put(e, new Image(new FileInputStream("src/main/resources/robot1.png")));
            this.playersImages.put(f, new Image(new FileInputStream("src/main/resources/robot2.png")));
            this.playersImages.put(g, new Image(new FileInputStream("src/main/resources/robot3.png")));
            this.playersImages.put(h, new Image(new FileInputStream("src/main/resources/robot4.png")));

            this.nothing = new Image(new FileInputStream("src/main/resources/square.png"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
