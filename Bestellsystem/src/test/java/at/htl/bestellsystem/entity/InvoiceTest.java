package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    @Test
    void createInvoice() {
        Service service = new Service(5L,"John","King");
        Desk desk = new Desk(5L,service);
        Bill bill = new Bill(2L,desk,service);
        Product product = new Product(5L,"Cola",2.2,new Dish("Getränke",4L));
        Invoice invoice = new Invoice(2L,10.2,6L,bill,product);
        assertThat(invoice.getId()).isEqualTo(2L);
        assertThat(invoice.getAmoutOfItem()).isEqualTo(6L);
        assertThat(invoice.getPrice()).isEqualTo(10.2);
    }
}