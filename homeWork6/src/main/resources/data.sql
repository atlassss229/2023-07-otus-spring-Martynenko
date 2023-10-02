insert into genres (genres_name)
values
('horror'),
('truth');

insert into authors (authors_name)
values
('Martins'),
('Lucius');

insert into books (book_name, book_year, author_id, genre_id)
values
('Dev Book', 2003, 1, 1),
('Bad Book', 2066, 2, 2);
