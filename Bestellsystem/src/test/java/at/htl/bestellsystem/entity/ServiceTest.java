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

}