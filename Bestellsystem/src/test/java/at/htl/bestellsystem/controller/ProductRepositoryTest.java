package at.htl.bestellsystem.controller;


import at.htl.bestellsystem.controller.DishRepository;
import at.htl.bestellsystem.controller.ProductRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Product;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository = new ProductRepository();
    DishRepository dishRepository = new DishRepository();

}