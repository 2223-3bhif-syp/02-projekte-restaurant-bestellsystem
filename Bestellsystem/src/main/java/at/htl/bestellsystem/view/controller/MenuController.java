package at.htl.bestellsystem.view.controller;

import at.htl.bestellsystem.view.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class MenuController {

    @FXML
    public Button foodButton;

    @FXML
    public Button dessertsButton;

    @FXML
    public Button myCartButton;

    @FXML
    public Button drinksButton;


    private void getNewStage(String name) throws IOException {
        Stage stage = App.getCurrentStage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + name + ".fxml"));

        Image icon = new Image("file:../../../images/logo.jpg");
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 795, 538);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onClickFoodButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Food Button Clicked!");
        getNewStage("food");
    }

    public void onClickDessertsButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Desserts Button Clicked!");
        getNewStage("desserts");
    }

    public void onClickMyCartButton(ActionEvent actionEvent) throws IOException{
        System.out.println("My Cart Button Clicked!");
        getNewStage("myCart");
    }

    public void onClickDrinksButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Drinks Button Clicked!");
        getNewStage("drinks");
    }
}
