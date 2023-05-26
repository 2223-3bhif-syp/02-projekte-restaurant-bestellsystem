package at.htl.bestellsystem.view.controller;

import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.view.App;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    public TextField nameField;
    public TextField priceField;
    public TextField amountField;
    @FXML
    public TextField searchField;


    ProductRepository productRepository = new ProductRepository();
    public ObservableList<Product> productObservableList = FXCollections.observableArrayList(productRepository.findAll());
    FilteredList<Product> productFl = new FilteredList<>(productObservableList, c -> true);

    public ListView foodLv;

    private void getNewStage(String name) throws IOException {
        Stage stage = App.getCurrentStage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + name + ".fxml"));

        Image icon = new Image("file:../../../images/logo.jpg");
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 795, 538);

        stage.setTitle("Pick Your Order");
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void initialize(){

        foodLv.setItems(productFl);

        this.foodLv.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Product>) (observableValue, contact1, t1) -> {
            Product product = observableValue.getValue();

            nameField.setText(product.getName() + "");
            priceField.setText(product.getPrice() + "");
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            productFl.setPredicate(contact -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                final String searchTerm = newValue.toLowerCase();

                return contact.getName().toLowerCase().contains(searchTerm);

            });
        });


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
