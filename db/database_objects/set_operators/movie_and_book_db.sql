CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- Фильмы, которые сняты по книге:
select name
from movie
INTERSECT
select title
from book;

-- Книги, у которых нет экранизации:
select title
from book
EXCEPT
select name
from movie;

-- Уникальные названия произведений из таблиц movie и book
--(фильмы, которые сняты не по книге, и книги без экранизации):
select name
from movie
EXCEPT
select title
from book
union
(select title
from book
EXCEPT
select name
from movie)
order by name;
