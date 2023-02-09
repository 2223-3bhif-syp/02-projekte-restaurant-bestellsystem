/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.controller;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceRepositoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testDish() {
        Table table = new Table(Database.getDataSource(), "INVOICE");
        output(table).toConsole();
    }

}