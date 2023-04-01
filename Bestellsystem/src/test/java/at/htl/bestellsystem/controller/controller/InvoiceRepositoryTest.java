
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);
        invoiceRepository.save(invoice);

        // test
        assertEquals(invoice.getId(), 1);

        assertThat(table).column("INVOICE_NR")
                .value().isEqualTo(invoice.getId());
        assertThat(table).column("BILL_NR")
                .value().isEqualTo(invoice.getBill().getId());
        assertThat(table).column("ITEM_NR")
                .value().isEqualTo(invoice.getProduct().getId());
        assertThat(table).column("AMOUNT_OF_ITEM")
                .value().isEqualTo(invoice.getAmoutOfItem());
        assertThat(table).column("PRICE")
                .value().isEqualTo(invoice.getPrice());
    }

    @Test
    void update() {
        // arrange
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.insert(invoice);

        invoice.setAmoutOfItem(4);
        invoice.setPrice(6.9);

        invoiceRepository.update(invoice);

        // test
        assertEquals(invoice.getId(), 1);

        assertThat(table).column("INVOICE_NR")
                .value().isEqualTo(invoice.getId());
        assertThat(table).column("BILL_NR")
                .value().isEqualTo(invoice.getBill().getId());
        assertThat(table).column("ITEM_NR")
                .value().isEqualTo(invoice.getProduct().getId());
        assertThat(table).column("AMOUNT_OF_ITEM")
                .value().isEqualTo(invoice.getAmoutOfItem());
        assertThat(table).column("PRICE")
                .value().isEqualTo(invoice.getPrice());
    }

    @Test
    void insert() {
        // arrange
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.insert(invoice);

        // test
        assertEquals(invoice.getId(), 1);

        assertThat(table).column("INVOICE_NR")
                .value().isEqualTo(invoice.getId());
        assertThat(table).column("BILL_NR")
                .value().isEqualTo(invoice.getBill().getId());
        assertThat(table).column("ITEM_NR")
                .value().isEqualTo(invoice.getProduct().getId());
        assertThat(table).column("AMOUNT_OF_ITEM")
                .value().isEqualTo(invoice.getAmoutOfItem());
        assertThat(table).column("PRICE")
                .value().isEqualTo(invoice.getPrice());
    }

    @Test
    void delete() {
        // arrange
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);
        invoiceRepository.insert(invoice);

        invoiceRepository.delete(invoice);

        // test
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        // arrange
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice3);

        List<Invoice> invoiceList = invoiceRepository.findAll();

        // test
        assertEquals(3, invoiceList.size());

        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice1.toString().equals(invoice.toString())));
        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice2.toString().equals(invoice.toString())));
        assertTrue(invoiceList.stream().anyMatch(invoice -> invoice3.toString().equals(invoice.toString())));
    }

    @Test
    void findById() {
        // arrange
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

        // modify
        dishRepository.save(dish);
        serviceRepository.save(service);
        deskRepository.save(desk);
        billRepository.save(bill);
        productRepository.save(product);

        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice3);

        // test
        assertEquals(invoice1.toString(), invoiceRepository.findById(invoice1.getId()).toString());
        assertEquals(invoice2.toString(), invoiceRepository.findById(invoice2.getId()).toString());
        assertEquals(invoice3.toString(), invoiceRepository.findById(invoice3.getId()).toString());
    }
}