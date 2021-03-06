package Asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsteroidsGame extends Application {
    private BorderPane root;
    private List<AsteroidsObject> bullets = new ArrayList<>();
    private List<AsteroidsObject> enemies = new ArrayList<>();

    private AsteroidsObject player;

    @FXML
    private Parent createContent() {
        root = new BorderPane();
        root.setPrefSize(600, 600);
        addBackground();
//        addFXML();

        player = new Player();
        player.setVelocity(new Point2D(1, 0));
        addAsteroidsObject(player, 300, 300);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }


//    private void addFXML(ActionEvent event)  {
//        BorderPane newLoadedPane = FXMLLoader.load(getClass().getResource("Asteroids.fxml"));
//        root.getChildren().add(newLoadedPane);
//    }

    private void addBullet(AsteroidsObject bullet, double x, double y) {
        bullets.add(bullet);
        addAsteroidsObject(bullet, x, y);
    }

    private void addEnemy(AsteroidsObject enemy, double x, double y) {
        enemies.add(enemy);
        addAsteroidsObject(enemy, x, y);
    }

    private void addAsteroidsObject(AsteroidsObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());

    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("background.jpg").toExternalForm()));
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);
    }

    private void onUpdate() {
        for (AsteroidsObject bullet : bullets) {
            for (AsteroidsObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);

                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }

        bullets.removeIf(AsteroidsObject::isDead);
        enemies.removeIf(AsteroidsObject::isDead);

        bullets.forEach(AsteroidsObject::update);
        enemies.forEach(AsteroidsObject::update);

        player.update();

        if (Math.random() < 0.02) {
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }

    private static class Player extends AsteroidsObject {
        Player() {
            super(new Rectangle(30, 20, Color.DARKGREY));
        }
    }

    private static class Enemy extends AsteroidsObject {
        Enemy() {
            super(new Circle(15, 15, 15, Color.RED));
        }
    }

    private static class Bullet extends AsteroidsObject {
        Bullet() {
            super(new Circle(5, 5, 5, Color.MIDNIGHTBLUE));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
            }
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
