package at.htlleonding.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Label label = new Label();

        Button button = new Button("Click me!");
        button.setOnAction((e)
                -> {
                    label.setText("hellooo!");
                    System.out.println("habub");
                });

        button.addEventHandler(ActionEvent.ACTION, (e)
            -> label.setText("hellooo!"));

        button.addEventHandler(ActionEvent.ACTION, (e)
            -> System.out.println("habub"));

        Yogamam ym = new Yogamam();
        button.addEventHandler(ActionEvent.ACTION, ym);

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, (a)
                -> System.out.println("Mouse Entered"));

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(button);
        box.getChildren().add(label);

        Scene scene = new Scene(box, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}