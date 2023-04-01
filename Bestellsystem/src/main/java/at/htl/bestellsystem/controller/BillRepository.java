
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Bill;
import at.htl.bestellsystem.entity.Desk;
import at.htl.bestellsystem.entity.Invoice;
import at.htl.bestellsystem.entity.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository implements Persistent<Bill>{

    private DataSource dataSource = Database.getDataSource();
    public void save(Bill bill) {
        if (bill.getId() == null) {
            insert(bill);
        } else {
            update(bill);
        }
    }

    @Override
    public void insert(Bill bill) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO BILL(WORKING_NR,DESK_NR) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, bill.getService().getId());
            statement.setLong(2, bill.getDesk().getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of Bill failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    bill.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into Bill failed, no ID obtained");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Bill bill) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM BILL WHERE BILL_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,bill.getId() );

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from Bill failed, no rows affected");
            }
            bill.setId(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> billList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM BILL";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ServiceRepository serviceRepository = new ServiceRepository();
                DeskRepository deskRepository = new DeskRepository();

                Long id = result.getLong("BILL_NR");
                Service service = serviceRepository.findById(result.getLong("WORKING_NR"));
                Desk desk = deskRepository.findById(result.getLong("DESK_NR"));
                Bill bill = new Bill(desk, service);
                bill.setId(id);

                billList.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billList;
    }

    @Override
    public Bill findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM BILL WHERE BILL_NR=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("BILL_NR")) {
                    DeskRepository deskRepository = new DeskRepository();
                    ServiceRepository serviceRepository = new ServiceRepository();
                    Bill bill = new Bill(deskRepository.findById(result.getLong("DESK_NR")),
                            serviceRepository.findById(result.getLong("WORKING_NR")));
                    bill.setId(id);

                    return bill;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Bill bill) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE BILL SET WORKING_NR=?,DESK_NR=? WHERE BILL_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, bill.getService().getId());
            statement.setLong(2, bill.getDesk().getId());
            statement.setLong(3, bill.getId());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Bill failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
