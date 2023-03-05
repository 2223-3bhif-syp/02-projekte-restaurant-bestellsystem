
package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DishRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Product;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DishRepositoryTest {
    DishRepository dishRepository = new DishRepository();
    Table table;

    @BeforeEach
    void setUp() {
        table = new Table(Database.getDataSource(), "DISH");
        SqlRunner.dropTablesAndCreateEmptyTables();

    }

    @Test
    void testDish() {
        output(table).toConsole();
    }

    @Test
    void shouldSaveDish() {
        dishRepository.save(createDish());

        org.assertj.db.api.Assertions.assertThat(table).row(0).value("NAME").isEqualTo("Dessert");


    }

    @Test
    void shouldUpdateWhenSavingExistingDish() {
        Dish dish = createDish();
        dishRepository.insert(dish);
        dish.setName("Vorspeise");
        dishRepository.save(dish);

        assertThat(table.getRowsList().size()).isEqualTo(1);
        org.assertj.db.api.Assertions.assertThat(table).row(0).value("NAME").isEqualTo("Vorspeise");


    }

    @Test
    void shouldNotInsertDishTwice() {
        Dish dish = createDish();
        dishRepository.insert(dish);

        int rowsBefore = table.getRowsList().size();
        dishRepository.insert(dish);
        int rowsAfter = table.getRowsList().size();

        assertThat(rowsBefore).isEqualTo(1);
        assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void shouldDeleteDish() {

        Dish dish = createDish();
        dishRepository.insert(dish);
        int rowBeforeDelete = table.getRowsList().size();

        assertThat(rowBeforeDelete).isEqualTo(1);

        dishRepository.delete(dish);
        int rowAfterDelete = table.getRowsList().size();
        org.assertj.core.api.Assertions.assertThat(rowAfterDelete).isEqualTo(rowAfterDelete);
    }

    @Test
    void shouldFindByIdOfDessert() {


        Dish dish1 = createDish();
        dishRepository.insert(dish1);

        Dish newDish = new Dish("Vorspeise");
        dishRepository.insert(newDish);

        assertEquals(newDish.getId(), dishRepository.findById(newDish).getId());
        assertEquals(dish1.getId(), dishRepository.findById(dish1).getId());

    }

    @Test
    void shouldGiveAllDishes() {
        Dish dish = new Dish("Dessert");
        Dish dish1 = new Dish("Vorspeise");
        Dish dish2 = new Dish("Suppe");

        dishRepository.insert(dish);
        dishRepository.insert(dish1);
        dishRepository.insert(dish2);
        List<Dish> dishes =  dishRepository.findAll();
        assertEquals(3,dishes.size());

    }

    private static Dish createDish() {
        Dish dish = new Dish("Dessert");
        return dish;
    }
}