package at.htl.bestellsystem.entity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    @Test
    void createInvoice() {
        Service service = new Service("John","King");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk,service);
        Product product = new Product("Cola",2.2,new Dish("Getränke"));
        Invoice invoice = new Invoice(10.2,6,bill,product);

        assertThat(invoice.getId()).isEqualTo(null);
        assertThat(invoice.getAmoutOfItem()).isEqualTo(6);
        assertThat(invoice.getPrice()).isEqualTo(10.2);
        assertThat(invoice.getBill()).isEqualTo(bill);
        assertThat(invoice.getProduct()).isEqualTo(product);
    }

    @Test
    void simpleConstructorGetters(){
        //arrange
        Service service = new Service("Markus","Müller");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk,service);
        Product product = new Product("Bier",2.5, new Dish("Getränke"));
        Invoice invoice = new Invoice(16.4,6,bill,product);


        //act

        //assert
        assertThat(invoice.getId()).isNull();
        assertThat(invoice.getAmoutOfItem()).isEqualTo(6);
        assertThat(invoice.getPrice()).isEqualTo(16.4);
        assertThat(invoice.getBill()).isEqualTo(bill);
        assertThat(invoice.getProduct().getPrice()).isEqualTo(2.5);
        assertThat(invoice.getProduct().getName()).isEqualTo("Bier");
        assertThat(invoice.getProduct().getDish().getName()).isEqualTo("Getränke");
    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Service service = new Service("Bertl","Bolasch");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk,service);
        Product product = new Product("Fanta",1.75, new Dish("Getränke"));
        Invoice invoice = new Invoice(5,3,bill,product);

        //act
        invoice.setId(94L);
        invoice.setPrice(5.5);
        invoice.getBill().getDesk().getService().setFirstName("Bolint");
        invoice.getProduct().setName("Cola");

        //assert
        assertThat(invoice.getId()).isEqualTo(94L);
        assertThat(invoice.getPrice()).isEqualTo(5.5);
        assertThat(invoice.getProduct().getDish().getName()).isEqualTo("Getränke");
        assertThat(invoice.getProduct().getName()).isEqualTo("Cola");
        assertThat(invoice.getBill().getDesk().getService().getFirstName()).isEqualTo("Bolint");
        assertThat(invoice.getBill().getDesk().getService().getLastName()).isEqualTo("Bolasch");
    }
}