package com.dao;

public enum SqlCommands {
    CATEGORY_INSERT_QUERY("INSERT INTO category(name) values(?);"),
    CATEGORY_RETURN_ID_QUERY("SELECT LAST_INSERT_ID() AS id"),
    CATEGORY_SELECT_QUERY("SELECT name FROM category WHERE id = ?"),
    CATEGORY_UPDATE_QUERY("UPDATE category SET name = ? WHERE id = ?"),
    CATEGORY_DELETE_QUERY("DELETE FROM category WHERE id = ?;"),
    CATEGORY_SELECT_ALL_QUERY("SELECT * FROM category"),
    BOOK_INSERT_QUERY("INSERT INTO book(title, category_id, author) VALUES (?,?,?);"),
    BOOK_RETURN_ID_QUERY("SELECT LAST_INSERT_ID() AS id"),
    BOOK_SELECT_QUERY("SELECT title, category_id, author FROM book WHERE id = ?"),
    BOOK_UPDATE_QUERY("UPDATE book SET title = ?, category_id = ?, author = ? WHERE id = ?;"),
    BOOK_DELETE_QUERY("DELETE FROM book WHERE id = ?"),
    BOOK_QUERY_SELECT_ALL("SELECT * FROM book");

    private final String query;

    SqlCommands(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
