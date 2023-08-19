insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Александр', 'Пушкин', '1799-05-26', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ( 'Лев', 'Толстой', '1828-08-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Михаил', 'Лермонтов', '1837-10-03', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ('Герберт', 'Шилдт', '1951-03-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ( 'Евгений', 'Петров', '1903-11-30', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (first_name, last_name, birth_date, created_date, modified_date)
values ( 'Илья', 'Ильф', '1897-10-15', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_genre (title,  created_date, modified_date)
values ('Проза', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (title,  created_date, modified_date)
values ('ИТ литература', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (title,  created_date, modified_date)
values ('Русская классика', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Герой нашего времени', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Война и мир', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (2, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Евгений Онегин', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Java 8. Полное руководство', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (4, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (4, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date)
values ('Двенадцать стульев', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());