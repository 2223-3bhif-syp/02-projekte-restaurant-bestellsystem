
package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.*;
import at.htl.bestellsystem.database.SqlRunner;
import at.htl.bestellsystem.entity.*;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceRepositoryTest {
    private static String tableName = "INVOICE";
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

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);
        Invoice invoice = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);
        invoiceRepository.save(invoice);

        output(table).toConsole();

        // assert
        assertEquals(invoice.getId(), 1);

        assertThat(table).exists()
                .row(0)
                .column("INVOICE_NR").value().isEqualTo(invoice.getId())
                .column("BILL_NR").value().isEqualTo(invoice.getBill().getId())
                .column("ITEM_NR").value().isEqualTo(invoice.getProduct().getPId())
                .column("AMOUNT_OF_ITEM").value().isEqualTo(invoice.getAmoutOfItem())
                .column("PRICE").value().isEqualTo(invoice.getPrice());
    }

    @Test
    void update() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);
        Invoice invoice = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.insert(invoice);

        invoice.setAmoutOfItem(4);
        invoice.setPrice(6.9);

        invoiceRepository.update(invoice);

        output(table).toConsole();

        // assert
        assertEquals(invoice.getId(), 1);

        assertThat(table).exists()
                .row(0)
                .column("INVOICE_NR").value().isEqualTo(invoice.getId())
                .column("BILL_NR").value().isEqualTo(invoice.getBill().getId())
                .column("ITEM_NR").value().isEqualTo(invoice.getProduct().getPId())
                .column("AMOUNT_OF_ITEM").value().isEqualTo(invoice.getAmoutOfItem())
                .column("PRICE").value().isEqualTo(invoice.getPrice());
    }

    @Test
    void insert() {
        // arrange
        output(table).toConsole();

        table = new Table(Database.getDataSource(), tableName);

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);
        Invoice invoice = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.insert(invoice);

        output(table).toConsole();

        // assert
        assertEquals(invoice.getId(), 1);

        assertThat(table).exists()
                .row(0)
                .column("INVOICE_NR").value().isEqualTo(invoice.getId())
                .column("BILL_NR").value().isEqualTo(invoice.getBill().getId())
                .column("ITEM_NR").value().isEqualTo(invoice.getProduct().getPId())
                .column("AMOUNT_OF_ITEM").value().isEqualTo(invoice.getAmoutOfItem())
                .column("PRICE").value().isEqualTo(invoice.getPrice());
    }

    @Test
    void delete() {
        // arrange
        output(table).toConsole();

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);
        Invoice invoice = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);
        invoiceRepository.insert(invoice);

        invoiceRepository.delete(invoice);

        output(table).toConsole();

        // assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
        output(table).toConsole();

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);

        Invoice invoice1 = new Invoice(4.6, 2, bill, product);
        Invoice invoice2 = new Invoice(4.6, 2, bill, product);
        Invoice invoice3 = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice3);

        List<Invoice> invoiceList = invoiceRepository.findAll();

        output(table).toConsole();

        // assert
        assertEquals(3, invoiceList.size());

        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice1.toString().equals(invoice.toString())));
        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice2.toString().equals(invoice.toString())));
        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice3.toString().equals(invoice.toString())));
    }

    @Test
    void findById() {
        // arrange
        output(table).toConsole();

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        DeskRepository deskRepository = new DeskRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        BillRepository billRepository = new BillRepository();
        ProductRepository productRepository = new ProductRepository();
        DishRepository dishRepository = new DishRepository();

        Dish dish = new Dish("dishName");
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill =  new Bill(desk, service);
        Product product = new Product("productName", 2.3, dish);

        Invoice invoice1 = new Invoice(4.6, 2, bill, product);
        Invoice invoice2 = new Invoice(4.6, 2, bill, product);
        Invoice invoice3 = new Invoice(4.6, 2, bill, product);

        // act
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice3);

        output(table).toConsole();

        // assert
        assertEquals(invoice1.toString(), invoiceRepository.findById(invoice1.getId()).toString());
        assertEquals(invoice2.toString(), invoiceRepository.findById(invoice2.getId()).toString());
        assertEquals(invoice3.toString(), invoiceRepository.findById(invoice3.getId()).toString());
    }
}