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
             PreparedStatement statement1 = connection.prepareStatement(SqlCommands.BOOK_INSERT_QUERY.toString());
             PreparedStatement statement2 = connection.prepareStatement(SqlCommands.BOOK_RETURN_ID_QUERY.toString())) {

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
        Book resultBook = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_SELECT_QUERY.toString())) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultBook = new Book();
                resultBook.setId(id);
                resultBook.setTitle(resultSet.getString("title"));
                resultBook.setAuthor(resultSet.getString("author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultBook;
    }

    public void updateBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_UPDATE_QUERY.toString())) {
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
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_DELETE_QUERY.toString())
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
             PreparedStatement statement = connection.prepareStatement(SqlCommands.BOOK_QUERY_SELECT_ALL.toString())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setCategory(categoryDAO.readCategory(resultSet.getLong("category_id")));
                book.setAuthor(resultSet.getString("author"));
                booksList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booksList;
    }
}
