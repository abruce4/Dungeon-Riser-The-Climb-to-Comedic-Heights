import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize UI components, set up event handlers, etc.
        primaryStage.setTitle("Dungeon Riser");

        Label storyLbl = new Label();

        // Create a StackPane layout
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, 800, 600);
        primaryStage.setScene(scene);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    storyLbl.setText(Main.displayStory());
                }
                catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        sp.getChildren().add(storyLbl);
        sp.getChildren().add(startButton);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}

