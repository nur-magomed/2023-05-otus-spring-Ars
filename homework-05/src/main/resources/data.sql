insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Aleksandr', 'Pushkin', '1799-05-26', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Lev', 'Tolstoy', '1828-08-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Mikhail', 'Lermontov', '1837-10-03', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Gerbert', 'Shildt', '1951-03-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Evgeniy', 'Petrov', '1903-11-30', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Ilya', 'Ilf', '1897-10-15', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_genre (title,  created_date, modified_date)
values ('Prose', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (title,  created_date, modified_date)
values ('IT literature', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (title,  created_date, modified_date)
values ('Russian classic', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('A hero of our time', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('War and peace', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (2, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Evgeniy Onegin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Java 8. The complete reference', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (4, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (4, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Twelve chairs', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());