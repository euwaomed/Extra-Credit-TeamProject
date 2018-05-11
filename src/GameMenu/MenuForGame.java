package GameMenu;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuForGame extends Application {

    private GameMenu gameMenu;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        root.setPrefSize(1920,1080);

        Image img = new Image("file:src/mortalkombat.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(1920);
        imgView.setFitHeight(1080);

        gameMenu = new GameMenu();

        root.getChildren().addAll(imgView, gameMenu);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5),gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    gameMenu.setVisible(true);
                    ft.play();
                }
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5),gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });



        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private class GameMenu extends StackPane
    {
        public GameMenu(){

            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);

            menu0.setTranslateX(400);
            menu0.setTranslateY(400);

            menu1.setTranslateX(400);
            menu1.setTranslateY(400);

            final int offset = 400;
            menu1.setTranslateX(offset);

            MenuButton btnAsteroids = new MenuButton("ASTEROIDS");
            btnAsteroids.setOnMouseClicked(event -> { });


            MenuButton btnPingPong = new MenuButton("PING-PONG");
            btnPingPong.setOnMouseClicked(event -> {
//        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
//        ft.setFromValue(1)
//        ft.setToValue(0);
//        ft.setOnFinished(e);
            });

            MenuButton btnConnect4 = new MenuButton("CONNECT 4");
            btnConnect4.setOnMouseClicked(event -> {
//        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
//        ft.setFromValue(1)
//        ft.setToValue(0);
//        ft.setOnFinished(e);
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu0.getChildren().addAll(btnAsteroids, btnPingPong, btnConnect4, btnExit);

            Rectangle bg = new Rectangle(1920,1080);
            bg.setFill(Color.BLACK);
            bg.setOpacity(0.2);

            getChildren().addAll(bg, menu0);

        }

    }

    private static class MenuButton extends StackPane
    {
        private Text text;

        public MenuButton(String name)
        {
            text = new Text(name);
            text.setFont(text.getFont().font(60));
            text.setFill(Color.ORANGERED);

            Rectangle bg = new Rectangle(350, 65);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(10));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event ->
            {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITESMOKE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.ORANGERED);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));

        }
    }



    public static void main(String[] args)
    {
        launch(args);
    }
}

