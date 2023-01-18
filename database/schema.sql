CREATE TABLE category
(
    id   bigint       NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL UNIQUE ,
    CONSTRAINT category_pk PRIMARY KEY (id)
);
CREATE TABLE book
(
    id          bigint       NOT NULL AUTO_INCREMENT,
    title       varchar(255) NOT NULL,
    category_id bigint       NOT NULL,
    author      varchar(255) NOT NULL,

    CONSTRAINT book_pk PRIMARY KEY (id)
);
ALTER TABLE book
    ADD CONSTRAINT book_fk0 FOREIGN KEY (category_id) REFERENCES category (id);