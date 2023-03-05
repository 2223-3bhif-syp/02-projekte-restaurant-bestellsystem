package at.htl.bestellsystem.controller.controller;


import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DishRepository;
import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Product;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository = new ProductRepository();
    DishRepository dishRepository = new DishRepository();
    Table table;

    @BeforeEach
    void setUp() {
        table = new Table(Database.getDataSource(), "Product");
        SqlRunner.dropTablesAndCreateEmptyTables();

    }

  //  @Test
  /*  void shouldInsertProduct() {
        Product product = new Product(2L,1L,"Cola",2.2,dishRepository.findById(new Dish("Getränk",2L)));
        productRepository.insert(product);
        int rowsBefore = table.getRowsList().size();
        productRepository.insert(product);
        int rowsAfter = table.getRowsList().size();

        assertThat(rowsBefore).isEqualTo(1);
        assertThat(rowsBefore).isEqualTo(rowsAfter);*/

    }
