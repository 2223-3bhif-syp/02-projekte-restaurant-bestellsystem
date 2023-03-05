package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeskTest {
    @Test
    void createDesk() {
        Desk desk = new Desk(2L,new Service(4L,"John","King"));
        assertThat(desk.getId()).isEqualTo(2L);
        assertThat(desk.getService().getLastName()).isEqualTo("King");
        assertThat(desk.getService().getFirstName()).isEqualTo("John");
    }

}