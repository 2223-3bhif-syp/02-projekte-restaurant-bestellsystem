package at.htl.bestellsystem.view.model;

import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Drink{
    public static Drink instance;

    public static synchronized Drink getInstance(){
        if(instance == null){
            SqlRunner.dropAndCreateTablesWithExampleData();
            instance = new Drink();
        }

        return instance;
    }

    private final ObservableList<Product> products;

    public Drink(){
        ProductRepository productRepository = new ProductRepository();
        this.products = FXCollections.observableArrayList(productRepository.findByDish(1L));
    }

    public FilteredList<Product> getDrink(){
        return new FilteredList<>(products, p -> true);
    }
}
