package at.htl.bestellsystem.view.model;

import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Dessert {
    public static Dessert instance;

    public static synchronized Dessert getInstance(){
        if(instance == null){
            SqlRunner.dropAndCreateTablesWithExampleData();
            instance = new Dessert();
        }

        return instance;
    }

    private final ObservableList<Product> products;

    public Dessert(){
        ProductRepository productRepository = new ProductRepository();
        this.products = FXCollections.observableArrayList(productRepository.findByDish(3L));
    }
    public FilteredList<Product> getDessert(){
        return new FilteredList<>(products, p -> true);
    }

}
