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
}