
package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.BillRepository;
import at.htl.bestellsystem.controller.Database;
import at.htl.bestellsystem.controller.DeskRepository;
import at.htl.bestellsystem.controller.ServiceRepository;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.Bill;
import at.htl.bestellsystem.entity.Desk;
import at.htl.bestellsystem.entity.Service;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;;

class DeskRepositoryTest {
    private static String tableName = "DESK";
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

        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);

        output(table).toConsole();

        // assert
        assertEquals(desk.getId(), 1);

        assertThat(table).column("DESK_NR")
                .value().isEqualTo(desk.getId());
        assertThat(table).column("WORKING_NR")
                .value().isEqualTo(desk.getService().getId());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Service newService = new Service("firstName", "lastName");

        // act
        serviceRepository.save(service);
        serviceRepository.save(newService);

        deskRepository.insert(desk);

        desk.setService(newService);
        deskRepository.update(desk);

        output(table).toConsole();

        // assert
        assertEquals(desk.getId(), 1);

        assertThat(table).column("DESK_NR")
                .value().isEqualTo(desk.getId());
        assertThat(table).column("WORKING_NR")
                .value().isEqualTo(desk.getService().getId());
    }

    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);

        // act
        serviceRepository.save(service);

        deskRepository.insert(desk);
        output(table).toConsole();

        // assert
        assertEquals(desk.getId(), 1);

        assertThat(table).column("DESK_NR")
                .value().isEqualTo(desk.getId());
        assertThat(table).column("WORKING_NR")
                .value().isEqualTo(desk.getService().getId());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);

        // act
        serviceRepository.save(service);
        deskRepository.insert(desk);

        deskRepository.delete(desk);

        output(table).toConsole();

        // assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");

        Desk desk1 = new Desk(service);
        Desk desk2 = new Desk(service);
        Desk desk3 = new Desk(service);


        // act
        serviceRepository.save(service);
        deskRepository.save(desk1);
        deskRepository.save(desk2);
        deskRepository.save(desk3);

        List<Desk> deskList = deskRepository.findAll();

        output(table).toConsole();

        // assert
        assertEquals(3, deskList.size());

        assertTrue(deskList.stream().anyMatch(desk -> desk1.toString().equals(desk.toString())));
        assertTrue(deskList.stream().anyMatch(desk -> desk2.toString().equals(desk.toString())));
        assertTrue(deskList.stream().anyMatch(desk -> desk3.toString().equals(desk.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");

        Desk desk1 = new Desk(service);
        Desk desk2 = new Desk(service);
        Desk desk3 = new Desk(service);


        // act
        serviceRepository.save(service);
        deskRepository.save(desk1);
        deskRepository.save(desk2);
        deskRepository.save(desk3);

        output(table).toConsole();

        // assert
        assertEquals(desk1.toString(), deskRepository.findById(desk1.getId()).toString());
        assertEquals(desk2.toString(), deskRepository.findById(desk2.getId()).toString());
        assertEquals(desk3.toString(), deskRepository.findById(desk3.getId()).toString());
    }
}