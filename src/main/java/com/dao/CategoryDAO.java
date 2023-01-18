package com.dao;

import com.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDAO {
    private final DataSource dataSource;

    @Autowired
    private CategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createCategory(Category category) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement2 = connection.prepareStatement(SqlCommands.CATEGORY_CREATE.toString());
             PreparedStatement statement1 = connection.prepareStatement(SqlCommands.RETURN_CATEGORY_ID.toString())
        ) {
            statement1.setString(1, category.getName());
            statement1.executeUpdate();

            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category readCategory(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_READ.toString())) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Category(id, resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; //TODO
    }

    public void updateCategory(Category category) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_UPDATE.toString())) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_DELETE.toString())) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categoriesList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_GET_ALL.toString())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categoriesList.add(new Category(resultSet.getLong("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoriesList;
    }
}
