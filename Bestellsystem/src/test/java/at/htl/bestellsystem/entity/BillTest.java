package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @Test
    void createBill() {
        Service service = new Service("John","King");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        assertThat(bill.getId()).isEqualTo(null);
        assertThat(bill.getDesk()).isEqualTo(desk);
        assertThat(bill.getDesk().getService()).isEqualTo(service);
    }

}