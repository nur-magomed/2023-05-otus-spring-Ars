DROP TABLE IF EXISTS t_author;
DROP TABLE IF EXISTS t_book;
DROP TABLE IF EXISTS t_genre;


CREATE TABLE t_author
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE,
    created_date DATE,
    modified_date DATE
);


CREATE TABLE t_book
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    created_date DATE,
    modified_date DATE
);


CREATE TABLE t_genre
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    created_date DATE,
    modified_date DATE
);


CREATE TABLE t_book_author
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT,
    author_id BIGINT,
    created_date DATE,
    modified_date DATE
);


CREATE TABLE t_book_genre
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT,
    genre_id BIGINT,
    created_date DATE,
    modified_date DATE
);