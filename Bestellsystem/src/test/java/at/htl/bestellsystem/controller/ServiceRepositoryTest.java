package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Service;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceRepositoryTest {
    ServiceRepository serviceRepositiory = new ServiceRepository();
    Table table;

    @BeforeEach
    void setUp() {
        table = new Table(Database.getDataSource(), "SERVICE");
        SqlRunner.dropTablesAndCreateEmptyTables();

    }

    @Test
    void testService() {
        Table table = new Table(Database.getDataSource(), "SERVICE");
        output(table).toConsole();
    }

    @Test
    void shouldSaveService() {
        Service service = new Service("John","King");
        Service service2 = new Service("John","King");
        serviceRepositiory.save(service);

        org.assertj.db.api.Assertions.assertThat(table).row(0).value("FIRST_NAME").isEqualTo("John");


    }

@Test
  void shouldUpdateWhenSavingExistingDish() {
    Service service = new Service("John","King");
        serviceRepositiory.insert(service);
        service.setFirstName("David");
        serviceRepositiory.save(service);

        assertThat(table.getRowsList().size()).isEqualTo(1);
    }


    @Test
    void shouldNotInsertDishTwice() {
        Service service = new Service("John","King");

        serviceRepositiory.insert(service);

        int rowsBefore = table.getRowsList().size();
        serviceRepositiory.insert(service);
        int rowsAfter = table.getRowsList().size();

        assertThat(rowsBefore).isEqualTo(1);
        assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void shouldDeleteDish() {
        Service service = new Service("John","King");


        serviceRepositiory.insert(service);
        int rowBeforeDelete = table.getRowsList().size();

        assertThat(rowBeforeDelete).isEqualTo(1);

        serviceRepositiory.delete(service);
        int rowAfterDelete = table.getRowsList().size();
        org.assertj.core.api.Assertions.assertThat(rowAfterDelete).isEqualTo(rowAfterDelete);
    }

    @Test
    void shouldFindByIdOfDessert() {

        Service service = new Service("John","King");
        Service service1 = new Service("Hai","Dello");


        serviceRepositiory.insert(service1);

        serviceRepositiory.insert(service);

        assertEquals(service.getId(), serviceRepositiory.findById(service).getId());
        assertEquals(service1.getId(), serviceRepositiory.findById(service1).getId());

    }

    @Test
    void shouldGiveAllDishes() {
        Service service = new Service("John","King");
        Service service2 = new Service("Mai","Chang");
        Service service3 = new Service("David","Joe");


        serviceRepositiory.insert(service);
        serviceRepositiory.insert(service2);
        serviceRepositiory.insert(service3);
        List<Service> dishes =  serviceRepositiory.findAll();
        assertEquals(3,dishes.size());

    }



}