create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax_first()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_first_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax_first();

insert into products (name, producer, count, price)
values ('product_1', 'producer_1', 5, 100);

insert into products (name, producer, count, price)
values ('product_2', 'producer_2', 3, 150);

select * from products;

create
or replace function tax_second()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_second_trigger
    after insert
    on products
    for each row
    execute procedure tax_second();

insert into products (name, producer, count, price)
values ('product_3', 'producer_3', 5, 200);

insert into products (name, producer, count, price)
values ('product_4', 'producer_4', 4, 250);

select * from products;

create table history_of_price
(
    ID    serial primary key,
    name  varchar(50),
    PRICE integer,
    date  timestamp
);

create
or replace function tax_third()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date) values
		(new.name, new.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_third_trigger
    after insert
    on products
    for each row
    execute procedure tax_third();

insert into products (name, producer, count, price)
values ('product_5', 'producer_5', 3, 300);

insert into products (name, producer, count, price)
values ('product_6', 'producer_6', 4, 350);

select * from history_of_price;
