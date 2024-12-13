create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people (
    id   serial primary key,
    name varchar(255)
);

create table devices_people (
    device_id int references devices (id),
    people_id int references people (id),
    primary key (device_id, people_id)
);

insert into devices (name, price) values ('Humane AI Pin', 79900);
insert into devices (name, price) values ('Lumax Wi-Fi Smart Plug', 2000);
insert into devices (name, price) values ('Brilliant Labs Frame', 35000);
insert into devices (name, price) values ('Mi Portable Electric Air Pump', 4500);
insert into devices (name, price) values ('Limitless Pendant', 9900);
insert into devices (name, price) values ('VTouch WIZPR', 19900);
insert into devices (name, price) values ('Xiaomi Mi Band 8', 4000);
insert into devices (name, price) values ('JBL GO 3', 4500);
insert into devices (name, price) values ('Rabbit R1', 19900);
insert into devices (name, price) values ('Vertex Impress Sunset NFC', 4999);

insert into people (name) values ('Ruslan'), ('Elena'), ('Maxsim'), ('Mikhail');

insert into devices_people (device_id, people_id) values
(1, 1), (2, 1), (7, 1), (9, 1),
(3, 2), (4, 2), (5, 2), (6, 2), (7, 2),
(8, 3),
(10, 4);

select avg(price) as "Средняя цена всех устройств"
from devices;

select p.name as Владелец, avg(d.price) as "Средняя цена его устройств"
from devices_people as dp
inner join devices as d on d.id = dp.device_id
inner join people as p on p.id = dp.people_id
group by p.name;

select p.name as Владелец, avg(d.price) as "Средняя цена его устройств"
from devices_people as dp
inner join devices as d on d.id = dp.device_id
inner join people as p on p.id = dp.people_id
group by p.name
having avg(d.price) > 5000
order by p.name;

