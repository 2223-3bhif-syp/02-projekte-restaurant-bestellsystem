package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeskTest {
    @Test
    void createDesk() {
        Service service = new Service("John","King");
        Desk desk = new Desk(service);

        assertThat(desk.getId()).isEqualTo(null);
        assertThat(desk.getService()).isEqualTo(service);
    }
}