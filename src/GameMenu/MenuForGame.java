package GameMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuForGame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        root.setPrefSize(1920,1080);



        Image img = new Image("file:src/mortalkombat.jpg");
        ImageView imgView = new ImageView(img);

        root.getChildren().addAll(imgView);

        Scene scene = new Scene(root);
        imgView.setFitWidth(1920);
        imgView.setFitHeight(1080);

        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args){
        launch(args);
    }
}

