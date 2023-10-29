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


insert into t_book (title,  created_date, modified_date, genre_id)
values ('A hero of our time', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date, genre_id)
values ('War and peace', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3);

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (2, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date, genre_id)
values ('Evgeniy Onegin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date, genre_id)
values ('Java 8. The complete reference', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 2);

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (4, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into t_book (title,  created_date, modified_date, genre_id)
values ('Twelve chairs', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3);

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into t_book_author (book_id, author_id,  created_date, modified_date)
values (5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


-- adding comments ---------------------------------------------------------------------------------------------------


insert into t_comment (book_id, message)
values (1, 'This book is relevant in all times');

insert into t_comment (book_id, message)
values (1, 'The most favorite book that written by Lermontov');

insert into t_comment (book_id, message)
values (2, 'What a great book Tolstoy wrote');

insert into t_comment (book_id, message)
values (2, 'Probably the most famous books of Tolstoy');

insert into t_comment (book_id, message)
values (3, 'I have read this hundred times');

insert into t_comment (book_id, message)
values (3, 'Pushkin was a genius');

insert into t_comment (book_id, message)
values (3, 'Evgeniy broke Tatianas heart');

insert into t_comment (book_id, message)
values (4, 'Must read for any Java developer');

insert into t_comment (book_id, message)
values (4, 'The best book about Java');

insert into t_comment (book_id, message)
values (4, 'This book is huge');

insert into t_comment (book_id, message)
values (5, 'The most funny book I have ever read');

insert into t_comment (book_id, message)
values (5, 'I have watched movie based on this book');