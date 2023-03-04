package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements Persistent<Service> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Service service) {
        if (service.getId() == null) {
            insert(service);
        } else {
            update(service);
        }
    }

    @Override
    public void insert(Service service) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO SERVICE(FIRST_NAME,LAST_NAME) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, service.getFirstName());
            statement.setString(2, service.getLastName());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of Service failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    service.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert Service Dish failed, no ID obtained");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void delete(Service service) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM SERVICE WHERE WORKING_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,service.getId() );

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from SERVICE failed, no rows affected");
            }
            service.setId(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Service> findAll() {
        List<Service> serviceList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM SERVICE";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("WORKING_NR");
                String firstName = result.getString("FIRST_NAME");
                String lastName = result.getString("LAST_NAME");


                serviceList.add(new Service(id,firstName,lastName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceList;

    }

    @Override
    public Service findById(Service service) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM SERVICE WHERE WORKING_NR=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, service.getId());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(service.getId() == result.getInt("WORKING_NR")) {
                    return new Service(result.getLong("WORKING_NR"),
                            result.getString("FIRST_NAME"),
                            result.getString("LAST_NAME"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void update(Service service) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE SERVICE SET FIRST_NAME=?,LAST_NAME=?  WHERE WORKING_NR=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, service.getFirstName());
            statement.setString(2, service.getLastName());
            statement.setLong(3, service.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Service failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
 }

