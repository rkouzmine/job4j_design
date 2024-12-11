create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
('African Elephant', 25550, '1798-07-21'),
('Snow Leopard', 9131, '1954-08-10'),
('Bald Eagle', 10000, '1782-06-20'),
('Atlantic Cod', 5475, '1758-03-01'),
('Swordfish', 3650, '1831-11-14'),
('Komodo Dragon', 7300, '1910-09-05'),
('Giant Panda', 9131, '1869-03-11'),
('Coelacanth', 7300, '1952-12-20'),
('Red Fox', 3650, '1758-06-10'),
('Blue Whale', 29200, '1911-12-05'),
('Monarch Butterfly', 365, '1874-09-01'),
('Green Sea Turtle', 25550, '1758-02-15'),
('Axolotl', 5475, '1965-03-15'),
('Emperor Penguin', 7300, '1844-01-12'),
('Clown Fish', 1825, '1869-02-25'),
('Flying Fish', 1095, '1758-05-20'),
('Unknown Creature', 10000, null),
('Pygmy Seahorse', 1095, '1969-11-01');

select * from fauna
where name ilike '%fish%';

select * from fauna
where avg_age between 10000 and 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where extract(year from discovery_date) < 1950;

select name, discovery_date
from fauna
where discovery_date is not null
group by name, discovery_date
order by discovery_date desc
limit 8;
