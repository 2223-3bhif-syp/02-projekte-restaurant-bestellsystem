
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class BillRepositoryTest {
    private static final String tableName = "BILL";
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

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        output(table).toConsole();  //reload the table

        // assert
        assertEquals(bill.getId(), 1);

        assertThat(table).exists()
                        .row(0)
                        .column("BILL_NR").value().isEqualTo(bill.getId())
                        .column("WORKING_NR").value().isEqualTo(bill.getService().getId())
                        .column("DESK_NR").value().isEqualTo(bill.getDesk().getId());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        Table table = new Table(Database.getDataSource(), tableName);

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Desk newDesk = new Desk(service);
        Bill bill = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);
        deskRepository.save(newDesk);

        billRepository.insert(bill);

        bill.setDesk(newDesk);
        billRepository.update(bill);

        output(table).toConsole();  //reload the table

        // assert
        assertEquals(bill.getId(), 1);

        assertThat(table).exists()
                .row(0)
                    .column("BILL_NR").value().isEqualTo(bill.getId())
                    .column("WORKING_NR").value().isEqualTo(bill.getService().getId())
                    .column("DESK_NR").value().isEqualTo(bill.getDesk().getId());
    }

    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        Table table = new Table(Database.getDataSource(), tableName);

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);

        billRepository.insert(bill);

        output(table).toConsole();  //reload the table

        // assert
        assertEquals(bill.getId(), 1);

        assertThat(table).exists()
                .row(0)
                    .column("BILL_NR").value().isEqualTo(bill.getId())
                    .column("WORKING_NR").value().isEqualTo(bill.getService().getId())
                    .column("DESK_NR").value().isEqualTo(bill.getDesk().getId());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.insert(bill);

        billRepository.delete(bill);

        output(table).toConsole();

        // assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);

        Bill bill1 = new Bill(desk, service);
        Bill bill2 = new Bill(desk, service);
        Bill bill3 = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill1);
        billRepository.save(bill2);
        billRepository.save(bill3);

        List<Bill> billList = billRepository.findAll();

        output(table).toConsole();

        // assert
        assertEquals(3, billList.size());

        assertTrue(billList.stream().anyMatch(bill -> bill1.toString().equals(bill.toString())));
        assertTrue(billList.stream().anyMatch(bill -> bill2.toString().equals(bill.toString())));
        assertTrue(billList.stream().anyMatch(bill -> bill3.toString().equals(bill.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();

        BillRepository billRepository = new BillRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        DeskRepository deskRepository = new DeskRepository();

        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);

        Bill bill1 = new Bill(desk, service);
        Bill bill2 = new Bill(desk, service);
        Bill bill3 = new Bill(desk, service);

        // act
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill1);
        billRepository.save(bill2);
        billRepository.save(bill3);

        output(table).toConsole();

        // assert
        assertEquals(bill1.toString(), billRepository.findById(bill1.getId()).toString());
        assertEquals(bill2.toString(), billRepository.findById(bill2.getId()).toString());
        assertEquals(bill3.toString(), billRepository.findById(bill3.getId()).toString());
    }
}