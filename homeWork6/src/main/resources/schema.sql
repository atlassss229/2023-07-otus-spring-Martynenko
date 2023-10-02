DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id           bigserial,
    authors_name varchar(255),
    primary key (id)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id          bigserial,
    genres_name varchar(255),
    primary key (id)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        bigserial,
    book_name varchar(255),
    book_year bigint,
    author_id bigint references authors (id),
    genre_id  bigint references genres (id),
    primary key (id)
);