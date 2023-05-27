package at.htl.bestellsystem.view.controller;

import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.view.App;
import at.htl.bestellsystem.view.model.Dessert;
import at.htl.bestellsystem.view.model.Drink;
import at.htl.bestellsystem.view.model.Food;
import at.htl.bestellsystem.view.model.MyCart;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

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

    public ListView<Product> foodListView;
    public ListView<Product> drinksListView;

    public ListView<Product> dessertListView;
    public ListView<Product> myCartListView;
    private Food food;

    private Drink drink;

    private Dessert dessert;
    private MyCart myCart;
    FilteredList<Product> filteredDrinks;
    FilteredList<Product> filteredFoods;

    FilteredList<Product> filteredDesserts;

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
        food = Food.getInstance();
        filteredFoods = new FilteredList<>(food.getFood());
        foodListView.setItems(filteredFoods);

        foodListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                priceField.setText(String.valueOf(newValue.getPrice()));
            }
        });

        drink = Drink.getInstance();
        filteredDrinks = new FilteredList<>(drink.getDrink());
        drinksListView.setItems(filteredDrinks);

        drinksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                priceField.setText(String.valueOf(newValue.getPrice()));
            }
        });

        dessert = Dessert.getInstance();
        filteredDesserts = new FilteredList<>(dessert.getDessert());
        dessertListView.setItems(filteredDesserts);

        dessertListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                priceField.setText(String.valueOf(newValue.getPrice()));
            }
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

    public void onClickAddButton(ActionEvent actionEvent) {
        Product selectedProduct = foodListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            MyCart.getInstance().addToCart(selectedProduct);
            System.out.println("Product added to cart: " + selectedProduct.getName());
        }
    }
}
