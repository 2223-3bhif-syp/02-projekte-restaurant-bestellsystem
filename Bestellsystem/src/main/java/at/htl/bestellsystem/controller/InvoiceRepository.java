
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Bill;
import at.htl.bestellsystem.entity.Invoice;
import at.htl.bestellsystem.entity.Product;
import at.htl.bestellsystem.entity.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements Persistent<Invoice>{

    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Invoice invoice) {
        if (invoice.getId() == null) {
            insert(invoice);
        } else {
            update(invoice);
        }
    }

    @Override
    public void insert(Invoice invoice) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO INVOICE(BILL_NR,ITEM_NR, AMOUNT_OF_ITEM, PRICE) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, invoice.getBillNr());
            statement.setLong(2, invoice.getItemNr());
            statement.setLong(3, invoice.getAmoutOfItem());
            statement.setDouble(4, invoice.getPrice());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of Invoice failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    invoice.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into Invoice failed, no ID obtained");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Invoice invoice) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM INVOICE WHERE INVOICE_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,invoice.getId() );

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from INVOICE failed, no rows affected");
            }
            invoice.setId(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> invoiceList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM INVOICE";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                BillRepository billRepository = new BillRepository();
                ProductRepository productRepository = new ProductRepository();

                Long id = result.getLong("INVOICE_NR");
                Bill bill = billRepository.findById(result.getLong("BILL_NR"));
                Product product = productRepository.findById(result.getLong("ITEM_NR"));
                Long amountOfItems = result.getLong("AMOUNT_OF_ITEM");
                Double price = result.getDouble("PRICE");

                invoiceList.add(new Invoice(id, price, amountOfItems, bill, product));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    @Override
    public Invoice findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM INVOICE WHERE INVOICE_NR=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("INVOICE_NR")) {
                    BillRepository billRepository = new BillRepository();
                    ProductRepository productRepository = new ProductRepository();

                    return new Invoice(id,
                            result.getDouble("PRICE"),
                            result.getLong("AMOUNT_OF_ITEM"),
                            billRepository.findById(result.getLong("BILL_NR")),
                            productRepository.findById(result.getLong("ITEM_NR")));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Invoice invoice) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE SERVICE SET BILL_NR=?,ITEM_NR=?, AMOUNT_OF_ITEM=?, PRICE=?  WHERE INVOICE_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, invoice.getBillNr());
            statement.setLong(2, invoice.getItemNr());
            statement.setLong(3, invoice.getAmoutOfItem());
            statement.setDouble(4, invoice.getPrice());
            statement.setLong(5, invoice.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Invoice failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}