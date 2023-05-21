package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DeskTest {

    @BeforeEach
    public void setUp(){

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void simpleConstructorGetters(){
        //arrange
        Service service = new Service("Johny", "Macler");
        Desk desk = new Desk(service);

        //act

        //assert
        assertThat(desk.getId()).isNull();
        assertThat(desk.getService()).isEqualTo(service);
        assertThat(desk.getService().getFirstName()).isEqualTo("Johny");
        assertThat(desk.getService().getLastName()).isEqualTo("Macler");

    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Service service = new Service("Johny", "Macler");
        Desk desk = new Desk(service);

        //act
        desk.setId(34907L);
        Service newService = new Service("anna", "hoifer");
        desk.setService(newService);

        //assert
        assertThat(desk.getId()).isEqualTo(34907L);
        assertThat(desk.getService()).isEqualTo(newService);
        assertThat(desk.getService().getFirstName()).isNotEqualTo("Johny");
    }

    @Test
    void defaultConstructorGetters(){
        //arrange
        Service service = new Service();
        Desk desk = new Desk(service);

        //act

        //assert
        assertThat(desk.getId()).isNull();
        assertThat(desk.getService().getFirstName()).isNull();
        assertThat(desk.getService().getLastName()).isNull();
    }

    @Test
    void defaultConstructorSetters(){
        //arrange
        Service service = new Service();
        Desk desk = new Desk(service);

        //act
        desk.setId(55L);
        Service newService = new Service("Omar", "Kabashi");
        desk.setService(newService);

        //assert
        assertThat(desk.getId()).isEqualTo(55L);
        assertThat(desk.getService()).isEqualTo(newService);
        assertThat(desk.getService().getLastName()).isEqualTo("Kabashi");

    }
}