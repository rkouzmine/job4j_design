create table city(
    id serial primary key,
    name varchar(255)
);

create table country(
    id serial primary key,
    name varchar(255),
    city_id int references city(id)
);

insert into city(name) values ('St. Petersburg');
insert into country(name, city_id) values ('Russia', '1');

SELECT city.name AS city_name, country.name AS country_name
FROM country
JOIN city ON country.city_id = city.id;