DROP TABLE IF EXISTS t_author;
DROP TABLE IF EXISTS t_book;
DROP TABLE IF EXISTS t_genre;


CREATE TABLE t_author (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date TIMESTAMP,
    created_date TIMESTAMP,
    modified_date TIMESTAMP
);


CREATE TABLE t_book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    genre_id BIGINT
);


CREATE TABLE t_genre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    created_date TIMESTAMP,
    modified_date TIMESTAMP
);


CREATE TABLE t_book_author (
    book_id BIGINT,
    author_id BIGINT,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES t_book(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES t_author(id) ON DELETE CASCADE
);