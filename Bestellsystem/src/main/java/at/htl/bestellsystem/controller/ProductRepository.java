
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Persistent<Product> {

    private DataSource dataSource = Database.getDataSource();
    private ProductRepository productRepository = new ProductRepository();
    private  DishRepository dishRepository = new DishRepository();
    @Override
    public void save(Product product) {
        if (product == null) {
            insert(product);
        } else {
            update(product);
        }

    }

    @Override
    public void insert(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO question (item_nr,dish_nr,name,price) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, product.getId());
            statement.setLong(2, product.getDishNr());
            statement.setString(3, product.getName());
            statement.setDouble(4, product.getPrice());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Product failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    product.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into Product failed, no ID obtained");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE item_nr=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, product.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from Product failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> findAll() {


        List<Product> productList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM product";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long item_nr = Long.valueOf(result.getInt("item_nr"));
                String name = result.getString("name");
                Long dish_nr = result.getLong("dish_nr");
                Double price = result.getDouble("price");

                ProductRepository productRepository = new ProductRepository();


                productList.add(new Product(item_nr, dish_nr, name, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product findById(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE item_nr=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, product.getId());
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                // long item_nr = result.getLong("item_nr");
                //Product product = productRepository.findById(item_nr);
                return new Product(
                        result.getLong("item_nr"),
                        result.getLong("dish_nr"),
                        result.getString("name"),
                        result.getDouble("price")

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void update(Product product) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE product  SET item_nr=?, dish_nr=?, name=? , price=? WHERE s_id=  " + product.getId();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, product.getId());
            statement.setLong(2, product.getDishNr());
            statement.setString(3, product.getName());
            statement.setDouble(4, product.getPrice());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Product failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
