CREATE TABLE if not exists authors
(
    id           bigserial,
    authors_name varchar(255),
    primary key (id)
);

CREATE TABLE if not exists genres
(
    id          bigserial,
    genres_name varchar(255),
    primary key (id)
);

CREATE TABLE if not exists books
(
    id        bigserial,
    book_name varchar(255),
    book_year bigint,
    author_id bigint references authors (id),
    genre_id  bigint references genres (id),
    primary key (id)
);

CREATE TABLE if not exists comments
(
    id bigserial,
    text varchar(255),
    book_id bigint references books (id),
    primary key (id)
);