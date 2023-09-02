insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (1, 'Александр', 'Пушкин', '1799-05-26', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (2, 'Лев', 'Толстой', '1828-08-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (3, 'Михаил', 'Лермонтов', '1837-10-03', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (4, 'Герберт', 'Шилдт', '1951-03-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (5, 'Евгений', 'Петров', '1903-11-30', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (6, 'Илья', 'Ильф', '1897-10-15', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_genre (id, title,  created_date, modified_date)
values (1, 'Проза', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (id, title,  created_date, modified_date)
values (2, 'ИТ литература', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (id, title,  created_date, modified_date)
values (3, 'Русская классика', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (1, 'Герой нашего времени', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (2, 'Война и мир', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (2, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (3, 'Евгений Онегин', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (3, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (4, 'Java 8. Полное руководство', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (4, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (4, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (5, 'Двенадцать стульев', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (5, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());