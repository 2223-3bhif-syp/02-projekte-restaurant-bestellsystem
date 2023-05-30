
package at.htl.bestellsystem.controller;

import at.htl.bestellsystem.entity.Dish;
import at.htl.bestellsystem.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Persistent<Product> {

    private final DataSource dataSource = Database.getDataSource();
    private final DishRepository dishRepository = new DishRepository();

    @Override
    public void save(Product product) {
        if (product.getPId() == null) {
            insert(product);
        } else {
            update(product);
        }
    }

    @Override
    public void insert(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO Product (dish_nr,name,price) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, product.getDish().getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Product failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    product.setPId(keys.getLong(1));
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
            statement.setLong(1, product.getPId());

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
                Long item_nr = result.getLong("item_nr");
                String name = result.getString("name");
                Long dish_nr = result.getLong("dish_nr");
                Double price = result.getDouble("price");
                Dish dish = dishRepository.findById(dish_nr);
                Product product = new Product(name, price, dish);
                product.setPId(item_nr);

               productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }



    @Override
    public Product findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE item_nr=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                Dish dish = dishRepository.findById(result.getLong("DISH_NR"));
                Product product = new Product(result.getString("name"),
                        result.getDouble("price"),
                        dish);
                product.setPId(id);

                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> findByDish(Long id) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE dish_nr=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                Dish dish = dishRepository.findById(result.getLong("DISH_NR"));
                Product product = new Product(result.getString("name"),
                        result.getDouble("price"),
                        dish);
                product.setPId(id);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public void update(Product product) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE PRODUCT  SET dish_nr=?, name=? , price=? WHERE ITEM_NR=  " + product.getPId();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, product.getDish().getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of Product failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
