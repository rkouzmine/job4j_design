create table products(
    id serial primary key,
    name varchar(255)
);

create table categories(
    id serial primary key,
    name varchar(255)
);

create table products_categories(
    id serial primary key,
    products_id int references products(id),
    categories_id int references categories(id)
);

insert into products(name) values ('RTX 100500');
insert into products(name) values ('Asus ROG');
insert into products(name) values ('Macbook air');
insert into products(name) values ('Samsung X GB');
insert into categories(name) values ('Laptop');
insert into categories(name) values ('Graphics Card');
insert into categories(name) values ('High-End SSD');
insert into products_categories (products_id, categories_id) values (1, 2), (2, 1), (3, 1), (4, 3);

select p.name as product, c.name as category
from products_categories as pc
inner join products as p on p.id = pc.products_id
inner join categories as c on c.id = pc.categories_id;