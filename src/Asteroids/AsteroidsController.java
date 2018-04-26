package Asteroids;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AsteroidsController {
    @FXML
    private BorderPane root;

    @FXML
    private ListView<AsteroidsGame> listView;

    @FXML
    public void initialize() {
        root = new BorderPane();
        root.setPrefSize(600, 600);
        root.getStylesheets().add(getClass().getResource("Aseroids.css").toExternalForm());
        listView.getItems();

    }

    @FXML
    public void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Defend your rectangle spaceship from the asteroids of death.");
        alert.show();
    }

    public void onExit() {
        System.exit(0);
    }
}
