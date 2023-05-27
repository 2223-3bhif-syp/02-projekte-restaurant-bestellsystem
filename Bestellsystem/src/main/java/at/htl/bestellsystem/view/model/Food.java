package at.htl.bestellsystem.view.model;

import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Food {
    public static Food instance;

    public static synchronized Food getInstance(){
        if(instance == null){
            instance = new Food();
        }

        return instance;
    }

    private final ObservableList<Product> products;

    public Food(){
        ProductRepository productRepository = new ProductRepository();
        this.products = FXCollections.observableArrayList(productRepository.findByDish(2L));
    }
    public FilteredList<Product> getFood(){
        return new FilteredList<>(products, p -> true);
    }

}
