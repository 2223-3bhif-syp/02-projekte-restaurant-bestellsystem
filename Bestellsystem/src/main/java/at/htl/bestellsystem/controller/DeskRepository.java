
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeskRepository implements Persistent<Desk>{
    private DataSource dataSource = Database.getDataSource();

    public void save(Desk desk) {
        if (desk.getId() == null) {
            insert(desk);
        } else {
            update(desk);
        }
    }

    @Override
    public void insert(Desk desk) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO DESK(WORKING_NR) VALUES (?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, desk.getService().getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of Desk failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    desk.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into Desk failed, no ID obtained");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Desk desk) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM DESK WHERE DESK_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,desk.getId() );

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from Desk failed, no rows affected");
            }
            desk.setId(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Desk> findAll() {
        List<Desk> deskList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DESK";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ServiceRepository serviceRepository = new ServiceRepository();

                Long id = result.getLong("DESK_NR");
                Service service = serviceRepository.findById(result.getLong("WORKING_NR"));
                Desk desk = new Desk(service);
                desk.setId(id);

                deskList.add(desk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deskList;
    }

    @Override
    public Desk findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DESK WHERE DESK_NR=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("DESK_NR")) {
                    ServiceRepository serviceRepository = new ServiceRepository();
                    Desk desk = new Desk(serviceRepository.findById(result.getLong("WORKING_NR")));
                    desk.setId(id);

                    return desk;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Desk desk) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE DESK SET WORKING_NR=?  WHERE DESK_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setLong(1, desk.getService().getId());
            statement.setLong(2, desk.getId());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Desk failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}