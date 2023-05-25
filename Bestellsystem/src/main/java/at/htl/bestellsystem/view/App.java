package at.htl.bestellsystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage currentStage = null;
    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/food.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 795, 538);
        currentStage.setTitle("Menu");

        Image icon = new Image("file:../../../images/logo.jpg");
        currentStage.getIcons().add(icon);

        currentStage.setScene(scene);
        currentStage.show();
    }

    public static Stage getCurrentStage(){
        return currentStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
