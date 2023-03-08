
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishRepository implements Persistent<Dish> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Dish dish) {
        if (dish.getId() == null) {
            insert(dish);
        } else {
            update(dish);
        }


    }

    @Override
    public void insert(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO DISH(NAME) VALUES (?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dish.getName());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of dish failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    dish.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into Dish failed, no ID obtained");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM DISH WHERE dish_nr=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,dish.getId() );

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from answer_option failed, no rows affected");
            }
            dish.setId(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DISH";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("DISH_NR");
                String name = result.getString("NAME");
                dishList.add(new Dish(id,name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishList;

    }

    @Override
    public Dish findById(Dish dish) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DISH WHERE DISH_NR=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, dish.getId());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(dish.getId() == result.getInt("dish_nr")) {
                    return new Dish(result.getLong("dish_nr"),
                            result.getString("name"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE DISH SET name=?  WHERE dish_nr=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dish.getName());
            statement.setLong(2, dish.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of DISH failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}