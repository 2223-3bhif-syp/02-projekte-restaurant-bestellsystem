package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @BeforeEach
    public void setUp(){

    }

    @AfterEach
    public void tearDown() {

    }
    @Test
    void createBill() {
        Service service = new Service("John","King");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        assertThat(bill.getId()).isEqualTo(null);
        assertThat(bill.getDesk()).isEqualTo(desk);
        assertThat(bill.getDesk().getService()).isEqualTo(service);
    }

    @Test
    void simpleConstructorGetters(){
        //arrange
        Service service = new Service("firstName", "lastName");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        //act

        //assert
        assertThat(bill.getId()).isNull();
        assertThat(bill.getService().getFirstName()).isEqualTo("firstName");
        assertThat(bill.getService().getLastName()).isEqualTo("lastName");

    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Service service = new Service("Abdul", "Bedo");
        Desk desk = new Desk(service);
        Bill bill = new Bill(desk, service);

        //act
        bill.setId(65L);
        bill.getService().setFirstName("Abdullah");
        bill.getService().setLastName("Aldesoky");

        //assert
        assertThat(bill.getId()).isEqualTo(65L);
        assertThat(bill.getService().getFirstName()).isEqualTo("Abdullah");
        assertThat(bill.getService().getLastName()).isEqualTo("Aldesoky");

    }

}