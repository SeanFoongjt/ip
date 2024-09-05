package elster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Path dataDir = Paths.get("data");
    private Elster elster = new Elster(dataDir);

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        MainWindow ap = MainWindow.of();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        ap.setElster(elster); // inject the Elster instance
        stage.show();
    }
}
