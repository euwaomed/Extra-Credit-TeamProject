package Asteroids;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class AsteroidsController {

    @FXML
    private ListView<AsteroidsGame> listView;

    @FXML
    public void initialize() {
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
