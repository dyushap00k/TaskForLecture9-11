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
             PreparedStatement statement1 = connection.prepareStatement(SqlCommands.CATEGORY_INSERT_QUERY.toString());
             PreparedStatement statement2 = connection.prepareStatement(SqlCommands.CATEGORY_RETURN_ID_QUERY.toString())
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
        Category resultCategory = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_SELECT_QUERY.toString())) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultCategory = new Category();
                resultCategory.setId(id);
                resultCategory.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultCategory;
    }

    public void updateCategory(Category category) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_UPDATE_QUERY.toString())) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_DELETE_QUERY.toString())) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categoriesList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.CATEGORY_SELECT_ALL_QUERY.toString())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                categoriesList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoriesList;
    }
}
