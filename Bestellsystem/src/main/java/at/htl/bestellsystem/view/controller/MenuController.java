package at.htl.bestellsystem.view.controller;

import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.view.App;
import at.htl.bestellsystem.view.model.Dessert;
import at.htl.bestellsystem.view.model.Drink;
import at.htl.bestellsystem.view.model.Food;
import at.htl.bestellsystem.view.model.MyCart;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MenuController<T> {

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
    public Button addBtn;
    public Label amountLabel;
    private Food food;

    private Drink drink;

    private Dessert dessert;
    private MyCart myCart;
    FilteredList<Product> filteredDrinks;
    FilteredList<Product> filteredFoods;

    FilteredList<Product> filteredDesserts;
    FilteredList<Product> filteredMyList;

    private void getNewStage(String name,String title) throws IOException {
        Stage stage = App.getCurrentStage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + name + ".fxml"));

        Image icon = new Image("file:../../../images/logo.jpg");
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 795, 538);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public void searchMethod(TextField field, FilteredList<Product> filteredList){
        String search = field.getText();

        filteredList.setPredicate(c -> Boolean.parseBoolean(c.getName()));

        filteredList.setPredicate(c ->   c.getName().toLowerCase().contains(search.toLowerCase()));

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

        myCartListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                priceField.setText(String.valueOf(newValue.getPrice()));
                amountField.setText(String.valueOf(newValue.getAmount()));
            }
        });

        myCart = MyCart.getInstance();
        filteredMyList = new FilteredList<>(myCart.getCartItems());
        myCartListView.setItems(filteredMyList);


    }

    public void onClickFoodButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Food Button Clicked!");
        getNewStage("food","Food");
    }

    public void onClickDessertsButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Desserts Button Clicked!");
        getNewStage("desserts","Desserts");
    }

    public void onClickMyCartButton(ActionEvent actionEvent) throws IOException{
        System.out.println("My Cart Button Clicked!");

        getNewStage("myCart","MyCart");
    }

    public void onClickDrinksButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Drinks Button Clicked!");
        getNewStage("drinks","Drinks");
    }

    public void addBtnHelper(ListView<Product> t){
        Product selectedProduct = t.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            System.out.println("Product added to cart: " + selectedProduct.getName());
            MyCart.getInstance().addToCart(selectedProduct);
            System.out.println(MyCart.getInstance().getCartItems().size());
            System.out.println(MyCart.getInstance().getCartItems().get(0));
            myCartListView.setItems(MyCart.getInstance().getCartItems());
        }

    }

    public void onClickAddButton(ActionEvent actionEvent) {
        Stage currentStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        System.out.println(currentStage.getTitle());

        switch (currentStage.getTitle()) {
            case "Food":
                addBtnHelper(foodListView);
                System.out.println("From foodListView added");


                break;
            case "Drinks":
                addBtnHelper(drinksListView);
                System.out.println("From drinksListView added");

                break;
            case "Desserts":
                addBtnHelper(dessertListView);
                System.out.println("From dessertListView added");
                break;

            default:
                System.out.println("");
                break;
        }
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        switch (currentStage.getTitle()) {
            case "Food":
                searchMethod(searchField,filteredFoods);
                System.out.println("Filtered foodListView");


                break;
            case "Drinks":
                searchMethod(searchField,filteredDrinks);
                System.out.println("Filtered drinksListView ");

                break;
            case "Desserts":
                searchMethod(searchField,filteredDesserts);
                System.out.println("Filtered dessertListView ");
                break;
            case "MyCart":
                searchMethod(searchField,filteredMyList);
                System.out.println("Filtered MycartListView ");
                break;

            default:
                System.out.println("");
                break;
        }


    }

    public void onSaveAction(ActionEvent actionEvent) {

        try{
            Product product =  myCartListView.getSelectionModel().getSelectedItem();
            int amount = Integer.parseInt( amountField.getText());
            product.setAmount(amount);
        }
        catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Incorrect data entered!");
            alert.showAndWait();
        }

    }

    public void onRemoveBtnAction(ActionEvent actionEvent) {
        try {
            myCart.removeFromCart(myCartListView.getSelectionModel().getSelectedItem());
            myCartListView.refresh();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Incorrect data entered!");
            alert.showAndWait();
        }
    }
}
