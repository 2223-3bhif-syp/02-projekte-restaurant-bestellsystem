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
    Table table = new Table(Database.getDataSource(), tableName);

    @BeforeEach
    public void setUp() {
        // to make sure every Table is empty and set up right
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
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);
        productRepository.save(product);

        output(table).toConsole();

        // assert
        assertEquals(product.getPId(), 1);

        Assertions.assertThat(table).exists()
                .row(0)
                        .column("ITEM_NR").value().isEqualTo(product.getPId())
                        .column("DISH_NR").value().isEqualTo(product.getDish().getId())
                        .column("NAME").value().isEqualTo(product.getName())
                        .column("PRICE").value().isEqualTo(product.getPrice());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);
        productRepository.insert(product);

        product.setPrice(2.5);
        productRepository.update(product);

        output(table).toConsole();

        // assert
        assertEquals(product.getPId(), 1);

        Assertions.assertThat(table).exists()
                        .row(0)
                                .column("ITEM_NR").value().isEqualTo(product.getPId())
                                .column("DISH_NR").value().isEqualTo(product.getDish().getId())
                                .column("NAME").value().isEqualTo(product.getName())
                                .column("PRICE").value().isEqualTo(product.getPrice());
    }
 
    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);

        productRepository.insert(product);

        output(table).toConsole();

        // assert
        assertEquals(product.getPId(), 1);

        Assertions.assertThat(table).exists()
                .row(0)
                        .column("ITEM_NR").value().isEqualTo(product.getPId())
                        .column("DISH_NR").value().isEqualTo(product.getDish().getId())
                        .column("NAME").value().isEqualTo(product.getName())
                        .column("PRICE").value().isEqualTo(product.getPrice());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");
        Product product = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);
        productRepository.insert(product);

        productRepository.delete(product);

        output(table).toConsole();

        // assert
        Assertions.assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");

        Product product1 = new Product("productName", 2.2, dish);
        Product product2 = new Product("productName", 2.2, dish);
        Product product3 = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = productRepository.findAll();
        output(table).toConsole();

        // assert
        assertEquals(3, productList.size());

        assertTrue(productList.stream().anyMatch(product -> product1.toString().equals(product.toString())));
        assertTrue(productList.stream().anyMatch(product -> product2.toString().equals(product.toString())));
        assertTrue(productList.stream().anyMatch(product -> product3.toString().equals(product.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();

        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("name");

        Product product1 = new Product("productName", 2.2, dish);
        Product product2 = new Product("productName", 2.2, dish);
        Product product3 = new Product("productName", 2.2, dish);

        // act
        dishRepository.save(dish);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        output(table).toConsole();

        // assert
        assertEquals(product1.toString(), productRepository.findById(product1.getPId()).toString());
        assertEquals(product2.toString(), productRepository.findById(product2.getPId()).toString());
        assertEquals(product3.toString(), productRepository.findById(product3.getPId()).toString());
    }
}
