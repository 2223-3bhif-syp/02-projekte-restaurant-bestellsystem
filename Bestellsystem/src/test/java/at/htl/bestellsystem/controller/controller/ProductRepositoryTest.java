package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.*;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.*;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRepositoryTest {
    private static String tableName = "PRODUCT";
    Table table;

    @BeforeEach
    public void setUp() {
        // to make sure every Table is empty and set up right
        table = new Table(Database.getDataSource(), tableName);
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    public void tearDown() {
        // to clear the tables again of all the test values
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void save() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);
        productRepository.save(product);

        // test
        assertEquals(product.getId(), 1);

        Assertions.assertThat(table).column("ITEM_NR")
                .value().isEqualTo(product.getId());
        Assertions.assertThat(table).column("DISH_NR")
                .value().isEqualTo(product.getDish().getId());
        Assertions.assertThat(table).column("NAME")
                .value().isEqualTo(product.getName());
        Assertions.assertThat(table).column("PRICE")
                .value().isEqualTo(product.getPrice());
    }

    @Test
    void update() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);
        productRepository.insert(product);

        product.setPrice(2.5);
        productRepository.update(product);

        // test
        assertEquals(product.getId(), 1);

        Assertions.assertThat(table).column("ITEM_NR")
                .value().isEqualTo(product.getId());
        Assertions.assertThat(table).column("DISH_NR")
                .value().isEqualTo(product.getDish().getId());
        Assertions.assertThat(table).column("NAME")
                .value().isEqualTo(product.getName());
        Assertions.assertThat(table).column("PRICE")
                .value().isEqualTo(product.getPrice());
    }
 
    @Test
    void insert() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);

        productRepository.insert(product);

        // test
        assertEquals(product.getId(), 1);

        Assertions.assertThat(table).column("ITEM_NR")
                .value().isEqualTo(product.getId());
        Assertions.assertThat(table).column("DISH_NR")
                .value().isEqualTo(product.getDish().getId());
        Assertions.assertThat(table).column("NAME")
                .value().isEqualTo(product.getName());
        Assertions.assertThat(table).column("PRICE")
                .value().isEqualTo(product.getPrice());
    }

    @Test
    void delete() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);
        productRepository.insert(product);

        productRepository.delete(product);

        // test
        Assertions.assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");

        Product product1 = new Product("productName", 2.2, dish);
        Product product2 = new Product("productName", 2.2, dish);
        Product product3 = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = productRepository.findAll();

        // test
        assertEquals(3, productList.size());

        assertTrue(productList.stream().anyMatch(product -> product1.toString().equals(product.toString())));
        assertTrue(productList.stream().anyMatch(product -> product2.toString().equals(product.toString())));
        assertTrue(productList.stream().anyMatch(product -> product3.toString().equals(product.toString())));
    }

    @Test
    void findById() {
        // arrange
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");

        Product product1 = new Product("productName", 2.2, dish);
        Product product2 = new Product("productName", 2.2, dish);
        Product product3 = new Product("productName", 2.2, dish);

        // modify
        dishRepository.save(dish);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // test
        assertEquals(product1.toString(), productRepository.findById(product1.getId()).toString());
        assertEquals(product2.toString(), productRepository.findById(product2.getId()).toString());
        assertEquals(product3.toString(), productRepository.findById(product3.getId()).toString());
    }
}
