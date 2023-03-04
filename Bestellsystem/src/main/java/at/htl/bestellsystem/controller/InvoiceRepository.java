
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Invoice;

import javax.sql.DataSource;

public class InvoiceRepository {

    private DataSource dataSource = Database.getDataSource();

}