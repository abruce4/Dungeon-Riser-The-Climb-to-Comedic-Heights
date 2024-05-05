import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize UI components, set up event handlers, etc.
        primaryStage.setTitle("Dungeon Riser");

        // Create a StackPane layout
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}

