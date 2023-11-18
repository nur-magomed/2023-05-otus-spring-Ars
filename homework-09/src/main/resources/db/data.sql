insert into t_author (first_name, last_name)
values ('Aleksandr', 'Pushkin');

insert into t_author (first_name, last_name)
values ('Lev', 'Tolstoy');

insert into t_author (first_name, last_name)
values ('Mikhail', 'Lermontov');

insert into t_author (first_name, last_name)
values ('Gerbert', 'Shildt');

insert into t_author (first_name, last_name)
values ('Evgeniy', 'Petrov');

insert into t_author (first_name, last_name)
values ('Ilya', 'Ilf');


insert into t_genre (title)
values ('Prose');

insert into t_genre (title)
values ('IT literature');

insert into t_genre (title)
values ('Russian classic');


insert into t_book (title, genre_id)
values ('A hero of our time', 1);

insert into t_book_author (book_id, author_id)
values (1, 3);


insert into t_book (title, genre_id)
values ('War and peace', 3);

insert into t_book_author (book_id, author_id)
values (2, 2);


insert into t_book (title, genre_id)
values ('Evgeniy Onegin', 1);

insert into t_book_author (book_id, author_id)
values (3, 1);


insert into t_book (title, genre_id)
values ('Java 8. The complete reference', 2);

insert into t_book_author (book_id, author_id)
values (4, 4);


insert into t_book (title, genre_id)
values ('Twelve chairs', 3);

insert into t_book_author (book_id, author_id)
values (5, 5);

insert into t_book_author (book_id, author_id)
values (5, 6);