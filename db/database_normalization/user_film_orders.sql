create type gender_enum as enum ('мужской', 'женский');

create table users(
id serial primary key,
name varchar(25),
surname varchar(25),
gender gender_enum
);

create table user_addresses(
id serial primary key,
street text not null,
house text not null,
flat text,
user_id int references users(id) on delete cascade
);

create table films (
id serial primary key,
name text unique not null
);

create table ordered_films(
id serial primary key,
film_id int references films(id),
user_id int references users(id) on delete cascade
);

insert into users(name, surname, gender) values
('Ольга', 'Егорова', 'женский'),
('Сергей', 'Иванов', 'мужской');

insert into user_addresses(street, house, flat, user_id) values
('1-ый Казанческий переулок', 'д. 14', null, 1),
('ул. Центральная', 'д. 40', 'кв. 74', 2),
('ул. Ленина', 'д. 7', 'кв. 130', 2);

insert into films(name) values
('Пираты Карибского моря'),
('Матрица: Революция'),
('Человек, который изменил все'),
('Интерстеллар');

insert into ordered_films(film_id, user_id) values
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(2, 2);
