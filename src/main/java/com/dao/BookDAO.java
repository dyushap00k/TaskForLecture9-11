package com.dao;

import com.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private final DataSource dataSource;
    private final CategoryDAO categoryDAO;

    private BookDAO(DataSource dataSource, @Autowired CategoryDAO categoryDAO) {
        this.dataSource = dataSource;
        this.categoryDAO = categoryDAO;
    }

    public void createBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(SqlCommands.BOOK_CREATE.toString());
             PreparedStatement statement2 = connection.prepareStatement(SqlCommands.BOOK_RETURN_ID.toString())) {

            statement1.setString(1, book.getTitle());
            statement1.setLong(2, book.getCategory().getId());
            statement1.setString(3, book.getAuthor());

            statement1.executeUpdate();

            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book readBook(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_READ.toString())) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(id, resultSet.getString("title"), categoryDAO.readCategory(resultSet.getLong("category_id")), resultSet.getString("author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_UPDATE.toString())) {
            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getCategory().getId());
            statement.setString(3, book.getAuthor());
            statement.setLong(4, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_DELETE.toString())
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        List<Book> booksList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_GET_ALL.toString())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                booksList.add(new Book(resultSet.getLong("id"), resultSet.getString("title"), categoryDAO.readCategory(resultSet.getLong("category_id")), resultSet.getString("author")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booksList;
    }
}
