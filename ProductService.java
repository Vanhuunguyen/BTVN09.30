package RepositoryService;

import DatabaseConnection.DatabaseCon;
import Repository.ProductRepo;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements ProductRepo {

    private boolean executeUpdate(String query, Product product, boolean isUpdate) {
        try (Connection connection = DatabaseCon.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            if (isUpdate) {
                preparedStatement.setInt(3, product.getId());
            } else {
                preparedStatement.setInt(3, product.getCategory().getId());
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseCon.printException(e);
        }
        return false;
    }


    @Override
    public boolean insertProduct(Product product) {
        String query = "INSERT INTO Product(name, price, cat_id) VALUES (?, ?, ?)";
        return executeUpdate(query, product, false);
    }

    @Override
    public boolean updateProduct(Product product) {
        String query = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        return executeUpdate(query, product, true);
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public List<Product> findProductsByCategory(String category_name) {
        return List.of();
    }
}
