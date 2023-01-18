package com.dao;

public enum SqlCommands {
    CATEGORY_CREATE {
        @Override
        public String toString() {
            return "SELECT LAST_INSERT_ID() AS id";
        }
    },
    RETURN_CATEGORY_ID{
        @Override
        public String toString() {
            return "INSERT INTO category(name) values(?);";
        }
    },
    CATEGORY_READ {
        @Override
        public String toString() {
            return "SELECT name FROM category WHERE id = ?";
        }
    },
    CATEGORY_UPDATE{
        @Override
        public String toString() {
            return "UPDATE category SET name = ? WHERE id = ?";
        }
    },
    CATEGORY_DELETE{
        @Override
        public String toString() {
            return "DELETE FROM category WHERE id = ?;";
        }
    },
    CATEGORY_GET_ALL{
        @Override
        public String toString() {
            return "SELECT * FROM category";
        }
    },
    BOOK_CREATE{
        @Override
        public String toString() {
            return "INSERT INTO book(title, category_id, author) VALUES (?,?,?);";
        }
    },
    BOOK_RETURN_ID{
        @Override
        public String toString() {
            return "SELECT LAST_INSERT_ID() AS id";
        }
    },
    BOOK_READ{
        @Override
        public String toString() {
            return "SELECT title, category_id, author FROM book WHERE id = ?";
        }
    },
    BOOK_UPDATE{
        @Override
        public String toString() {
            return "UPDATE book SET title = ?, category_id = ?, author = ? WHERE id = ?;";
        }
    },
    BOOK_DELETE{
        @Override
        public String toString() {
            return "DELETE FROM book WHERE id = ?";
        }
    },
    BOOK_GET_ALL{
        @Override
        public String toString() {
            return "SELECT * FROM book";
        }
    }
}
