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
        Bill bill = new Bill(2L,
                new Desk(3L,new Service(5L,"John","King"))
                ,new Service(5L,"John","King"));
        assertThat(bill.getId()).isEqualTo(2L);
        assertThat(bill.getDesk().getId()).isEqualTo(3L);
        assertThat(bill.getDesk().getService().getId()).isEqualTo(5L);
        assertThat(bill.getService().getId()).isEqualTo(5L);




    }

}