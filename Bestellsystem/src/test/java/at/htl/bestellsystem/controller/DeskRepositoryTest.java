/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.controller;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.db.output.Outputs.output;;

class DeskRepositoryTest {

    @BeforeEach
    void setUp() {

    }
    @Test
    void testService() {
        Table table = new Table(Database.getDataSource(), "DESK");
        output(table).toConsole();
    }

}