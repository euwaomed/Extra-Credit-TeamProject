import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class SampleApp extends Application
{

    private static final String title = "Hello World";
    private static final int width = 300, height = 275;
    private static final String fxml_path = "sample.fxml";

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        URL fxml_url = getClass().getResource(fxml_path);
        loader.setLocation(fxml_url);
        Parent root = loader.load();
        stage.setTitle(title);
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
