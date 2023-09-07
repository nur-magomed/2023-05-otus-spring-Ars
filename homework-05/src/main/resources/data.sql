insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100001, 'Aleksandr', 'Pushkin', '1799-05-26', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100002, 'Lev', 'Tolstoy', '1828-08-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100003, 'Mikhail', 'Lermontov', '1837-10-03', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100004, 'Gerbert', 'Shildt', '1951-03-28', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100005, 'Evgeniy', 'Petrov', '1903-11-30', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_author (id, first_name, last_name, birth_date, created_date, modified_date)
values (100006, 'Ilya', 'Ilf', '1897-10-15', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_genre (id, title,  created_date, modified_date)
values (100001, 'Prose', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (id, title,  created_date, modified_date)
values (100002, 'IT literature', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_genre (id, title,  created_date, modified_date)
values (100003, 'Russian classic', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (100001, 'A hero of our time', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100001, 100003, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100001, 100001, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100001, 100003, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (100002, 'War and peace', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100002, 100002, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100002, 100001, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100002, 100003, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (100003, 'Evgeniy Onegin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100003, 100001, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100003, 100001, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100003, 100003, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (100004, 'Java 8. The complete reference', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100004, 100004, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100004, 100002, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (id, title,  created_date, modified_date)
values (100005, 'Twelve chairs', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100005, 100005, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (100005, 100006, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100005, 100001, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_genre (book_id, genre_id,  created_date, modified_date)
values (100005, 100003, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());