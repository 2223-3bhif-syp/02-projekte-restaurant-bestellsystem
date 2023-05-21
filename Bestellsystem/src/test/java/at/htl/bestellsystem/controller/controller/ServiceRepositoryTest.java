package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DeskRepository;
import at.htl.bestellsystem.controller.ServiceRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Desk;
import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Service;
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

class ServiceRepositoryTest {
    private static String tableName = "SERVICE";
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
        output(table).toConsole();

        Table table = new Table(Database.getDataSource(), tableName);

        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = new Service("firstName", "lastName");

        // act
        serviceRepository.save(service);

        output(table).toConsole();

        // assert
        assertEquals(service.getId(), 1);

        Assertions.assertThat(table).column("WORKING_NR")
                .value().isEqualTo(service.getId());
        Assertions.assertThat(table).column("FIRST_NAME")
                .value().isEqualTo(service.getFirstName());
        Assertions.assertThat(table).column("LAST_NAME")
                .value().isEqualTo(service.getLastName());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        Table table = new Table(Database.getDataSource(), tableName);

        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = new Service("firstName", "lastName");

        // act
        serviceRepository.save(service);

        service.setLastName("lastName2");
        serviceRepository.update(service);
        output(table).toConsole();

        // assert
        assertEquals(service.getId(), 1);

        Assertions.assertThat(table).column("WORKING_NR")
                .value().isEqualTo(service.getId());
        Assertions.assertThat(table).column("FIRST_NAME")
                .value().isEqualTo(service.getFirstName());
        Assertions.assertThat(table).column("LAST_NAME")
                .value().isEqualTo(service.getLastName());
    }

    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        Table table = new Table(Database.getDataSource(), tableName);

        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = new Service("firstName", "lastName");

        // act
        serviceRepository.insert(service);

        output(table).toConsole();

        // assert
        assertEquals(service.getId(), 1);

        Assertions.assertThat(table).column("WORKING_NR")
                .value().isEqualTo(service.getId());
        Assertions.assertThat(table).column("FIRST_NAME")
                .value().isEqualTo(service.getFirstName());
        Assertions.assertThat(table).column("LAST_NAME")
                .value().isEqualTo(service.getLastName());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();
        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = new Service("firstName", "lastName");

        // act
        serviceRepository.insert(service);

        serviceRepository.delete(service);
        output(table).toConsole();

        // assert
        Assertions.assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        ServiceRepository serviceRepository = new ServiceRepository();
        Service service1 = new Service("firstName", "lastName");
        Service service2 = new Service("firstName", "lastName");
        Service service3 = new Service("firstName", "lastName");

        // act
        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);

        List<Service> serviceList = serviceRepository.findAll();

        output(table).toConsole();

        // assert
        assertEquals(3, serviceList.size());

        assertTrue(serviceList.stream().anyMatch(service -> service1.toString().equals(service.toString())));
        assertTrue(serviceList.stream().anyMatch(service -> service2.toString().equals(service.toString())));
        assertTrue(serviceList.stream().anyMatch(service -> service3.toString().equals(service.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();

        ServiceRepository serviceRepository = new ServiceRepository();
        Service service1 = new Service("firstName", "lastName");
        Service service2 = new Service("firstName", "lastName");
        Service service3 = new Service("firstName", "lastName");

        // act
        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);

        output(table).toConsole();

        // assert
        assertEquals(service1.toString(), serviceRepository.findById(service1.getId()).toString());
        assertEquals(service2.toString(), serviceRepository.findById(service2.getId()).toString());
        assertEquals(service3.toString(), serviceRepository.findById(service3.getId()).toString());
    }
}