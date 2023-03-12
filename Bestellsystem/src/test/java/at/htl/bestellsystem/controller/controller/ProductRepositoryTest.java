package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DishRepository;
import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.controller.ServiceRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.entity.Service;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {

    ProductRepository productRepository = new ProductRepository();
    DishRepository dishRepository = new DishRepository();
    Table table;

    @BeforeEach
    void setUp() {
        table = new Table(Database.getDataSource(), "PRODUCT");
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void testProduct() {
        Table table = new Table(Database.getDataSource(), "PRODUCT");
        output(table).toConsole();
    }

    @Test
    void shouldInsertProduct() {
        Product product = new Product(1L, "Cola", 2.2, new Dish("Getränke", 1L));
        productRepository.insert(product);
        int rowsBefore = table.getRowsList().size();
        productRepository.insert(product);
        int rowsAfter = table.getRowsList().size();

        assertThat(rowsBefore).isEqualTo(1);
        assertThat(rowsBefore).isEqualTo(rowsAfter);

    }
}
