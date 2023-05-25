package at.htl.bestellsystem.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.w3c.dom.Text;

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


    @FXML
    private void initialize() throws SQLException {


    }

    public void onClickFoodButton(ActionEvent actionEvent) {
        System.out.println("Food Button Clicked!");
    }

    public void onClickDessertsButton(ActionEvent actionEvent) {
        System.out.println("Desserts Button Clicked!");
    }

    public void onClickMyCartButton(ActionEvent actionEvent) {
        System.out.println("My Cart Button Clicked!");
    }

    public void onClickDrinksButton(ActionEvent actionEvent) {
        System.out.println("Drinks Button Clicked!");
    }
}
