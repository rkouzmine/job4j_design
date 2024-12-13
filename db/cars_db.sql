create TABLE cars (
   id serial primary key,
    model text NOT NULL,
    release date NOT NULL
);

create TABLE colors (
 id serial primary key,
 color text NOT NULL
);

create TABLE cars_colors (
car_id int not null references cars(id) ON delete CASCADE,
color_id int not null references colors(id) ON delete CASCADE,
PRIMARY KEY (car_id, color_id )
);

create TABLE configurations (
    id serial primary key,
    engine_volume decimal NOT NULL,
    engine_power int NOT NULL,
    car_id int references cars(id) ON delete CASCADE
);

create TABLE prices (
     id serial primary key,
     amount decimal NOT NULL,
     configuration_id int references configurations(id) ON delete CASCADE,
     color_id int references colors(id) ON delete CASCADE
);

insert into cars (model, release) values
('Geely Coolray', '2018-08-22'),
('Toyota Corolla', '2020-01-15'),
('Honda Civic', '2019-05-10'),
('BMW 3 Series', '2022-01-01'),
('Volkswagen Golf', '2021-01-01'),
('Ford Focus', '2022-01-01');

insert into colors (color) values
('blue'),
('white'),
('black'),
('gray'),
('red');

insert into cars_colors (car_id, color_id) values
(1, 1), (1, 3),
(2, 3), (2, 2), (2, 4),
(3, 2), (3, 3), (3, 4),
(4, 3),
(5, 1), (5, 2),
(6, 1), (6, 3);


insert into configurations (engine_volume, engine_power, car_id) values
(1.5, 147, 1),
 (2.0, 170, 1),
(1.8, 140, 2),
 (2.0, 160, 2),
(1.5, 174, 3),
(2.0, 158, 3),
(2.0, 255, 4),
(1.5, 150, 5),
(2.0, 250, 6);

insert into prices (amount, configuration_id, color_id) values
(1800000, 1, 1),
(1850000, 1, 3),
(2000000, 2, 1),
(2050000, 2, 3),
(1500000, 3, 2),
(1700000, 4, 4),
(1900000, 5, 2),
(2100000, 6, 3),
(2500000, 7, 3),
(2200000, 8, 1),
(4000000, 9, 3);

select c.model as Cars, count(cs.color) as "Number of available colors"
from cars c
inner join cars_colors as cc on c.id = cc.car_id
inner join colors as cs on cc.color_id = cs.id
group by c.model;

select
c.model as Авто,
conf.engine_volume as "Объем двигателя",
conf.engine_power as "Мощность двигателя"
from cars as c
inner join configurations as conf on c.id = conf.car_id
where engine_volume > 1.5;

select
c.model as Авто,
cs.color as Цвет,
conf.engine_volume as "Объем двигателя",
conf.engine_power as "Мощность двигателя",
extract(year from c.release) as "Год выпука",
p.amount as Цена
from cars as c
inner join cars_colors as cc on c.id = cc.car_id
inner join colors as cs on cc.color_id = cs.id
inner join configurations as conf on c.id = conf.car_id
inner join prices as p on conf.id = p.configuration_id and cs.id = p.color_id;
