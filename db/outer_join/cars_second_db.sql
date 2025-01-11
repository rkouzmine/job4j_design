create table car_bodies (
id serial primary key,
name varchar(255)
);

create table car_engines (
id serial primary key,
name varchar(255)
);

create table car_transmissions (
id serial primary key,
name varchar(255)
);

create table cars (
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values
('Sedan'),
('Coupe'),
('Station Wagon'),
('Hatchback'),
('Convertible'),
('Minivan'),
('SUV'),
('Crossover');

insert into car_engines (name) values
('Gasoline'),
('Diesel'),
('Electric'),
('Hybrid ');

insert into car_transmissions (name) values
('MT'),
('AT'),
('AMT'),
('CVT'),
('DSG/DCT');

insert into cars (name, body_id, engine_id, transmission_id) values
    ('Toyota Camry', 1, 1, 2),
    ('Honda Civic', 1, 1, null),
    ('Tesla Model S', 1, null, null),
    ('Ford Mustang', 2, 1, 2),
    ('BMW X5', 8, 2, 5),
    ('Chevrolet Tahoe', 7, 2, 2),
    ('Volkswagen Golf', 4, 1, 5),
    ('Toyota Prius', 1, null, 4),
    ('Mazda CX-5', 8, 1, 2),
    ('Jeep Wrangler', 7, 1, null),
    ('Chrysler Pacifica', 6, null, 2),
    ('Porsche 911', 2, 1, null),
    ('Audi A6 Avant', null, 2, 5),
    ('Mini Cooper', 4, 1, 4),
    ('Ford Fiesta', 4, 1, null);

select * from cars as c
inner join car_bodies as cb on cb.id = c.body_id
inner join car_engines as ce on ce.id = c.engine_id
inner join car_transmissions as ct on ct.id = c.transmission_id;

select c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars as c
left outer join car_bodies as cb on c.body_id = cb.id
left outer join car_engines as ce on c.engine_id  = ce.id
left outer join car_transmissions as ct on c.transmission_id = ct.id;

select cb.name as "Bodies that are not in use"
from car_bodies as cb
left outer join cars as c on c.body_id = cb.id
where c.name is null;

select ce.name as "Engines that are not in use"
from car_engines as ce
left outer join cars as c on c.engine_id = ce.id
where c.name is null;

select ct.name as "Transmission that are not in use"
from car_transmissions as ct
left outer join cars as c on c.transmission_id = ct.id
where c.name is null;
