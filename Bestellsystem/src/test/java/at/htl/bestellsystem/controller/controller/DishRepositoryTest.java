
package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DishRepository;
import at.htl.bestellsystem.controller.ServiceRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.entity.Service;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DishRepositoryTest {
    private static String tableName = "DISH";
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

        DishRepository dishRepository = new DishRepository();
        Dish dish = new Dish("name");

        // act
        dishRepository.save(dish);
        output(table).toConsole();

        // assert
        assertEquals(dish.getId(), 1);

        assertThat(table).exists()
                .row(0)
                        .column("DISH_NR").value().isEqualTo(dish.getId())
                        .column("NAME").value().isEqualTo(dish.getName());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        DishRepository dishRepository = new DishRepository();
        Dish dish = new Dish("name");

        // act
        dishRepository.save(dish);

        dish.setName("name2");
        dishRepository.update(dish);

        output(table).toConsole();

        // assert
        assertEquals(dish.getId(), 1);

        assertThat(table).exists()
                .row(0)
                        .column("DISH_NR").value().isEqualTo(dish.getId())
                        .column("NAME").value().isEqualTo(dish.getName());
    }

    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        DishRepository dishRepository = new DishRepository();
        Dish dish = new Dish("name");

        // act
        dishRepository.insert(dish);

        output(table).toConsole();

        // assert
        assertEquals(dish.getId(), 1);

        assertThat(table).exists()
                .row(0)
                        .column("DISH_NR").value().isEqualTo(dish.getId())
                        .column("NAME").value().isEqualTo(dish.getName());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();

        DishRepository dishRepository = new DishRepository();
        Dish dish = new Dish("name");

        // act
        dishRepository.insert(dish);

        dishRepository.delete(dish);

        output(table).toConsole();

        // assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        DishRepository dishRepository = new DishRepository();
        Dish dish1 = new Dish("name");
        Dish dish2 = new Dish("name");
        Dish dish3 = new Dish("name");

        // act
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);

        List<Dish> dishList = dishRepository.findAll();

        output(table).toConsole();

        // assert
        assertEquals(3, dishList.size());

        assertTrue(dishList.stream().anyMatch(dish -> dish1.toString().equals(dish.toString())));
        assertTrue(dishList.stream().anyMatch(dish -> dish2.toString().equals(dish.toString())));
        assertTrue(dishList.stream().anyMatch(dish -> dish3.toString().equals(dish.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();

        DishRepository dishRepository = new DishRepository();
        Dish dish1 = new Dish("name");
        Dish dish2 = new Dish("name");
        Dish dish3 = new Dish("name");

        // act
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);

        output(table).toConsole();

        // assert
        assertEquals(dish1.toString(), dishRepository.findById(dish1.getId()).toString());
        assertEquals(dish2.toString(), dishRepository.findById(dish2.getId()).toString());
        assertEquals(dish3.toString(), dishRepository.findById(dish3.getId()).toString());
    }
}