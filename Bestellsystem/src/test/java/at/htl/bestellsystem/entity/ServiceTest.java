package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void createService() {
        Service service = new Service("John","King");

        assertThat(service.getId()).isEqualTo(null);
        assertThat(service.getFirstName()).isEqualTo("John");
        assertThat(service.getLastName()).isEqualTo("King");
    }

    @Test
    void simpleConstructorGetters(){
        //arrange
        Service service = new Service("John","King");

        //act

        //assert
        assertThat(service.getFirstName()).isEqualTo("John");
        assertThat(service.getLastName()).isEqualTo("King");

    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Service service = new Service("John","King");

        //act
        service.setId(2L);
        service.setFirstName("Abdullah");
        service.setLastName("AlDesoky");

        //assert
        assertThat(service.getId()).isEqualTo(2L);
        assertThat(service.getFirstName()).isEqualTo("Abdullah");
        assertThat(service.getLastName()).isEqualTo("AlDesoky");
    }

    @Test
    void defaultConstructorGetters(){
        //arrange
        Service service = new Service();

        //act

        //assert
        assertThat(service.getId()).isNull();
        assertThat(service.getFirstName()).isNull();
        assertThat(service.getLastName()).isNull();
    }

    @Test
    void defaultConstructorSetters(){
        //arrange
        Service service = new Service();

        //act
        service.setId(2L);
        service.setFirstName("Abdullah");
        service.setLastName("AlDesoky");

        //assert
        assertThat(service.getId()).isEqualTo(2L);
        assertThat(service.getFirstName()).isEqualTo("Abdullah");
        assertThat(service.getLastName()).isEqualTo("AlDesoky");
    }
}