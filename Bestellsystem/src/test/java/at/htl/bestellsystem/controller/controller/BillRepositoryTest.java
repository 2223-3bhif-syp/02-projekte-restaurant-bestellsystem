
package at.htl.bestellsystem.controller.controller;

import at.htl.bestellsystem.controller.Database;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.db.output.Outputs.output;

class BillRepositoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testService() {
        Table table = new Table(Database.getDataSource(), "BILL");
        output(table).toConsole();
    }

}