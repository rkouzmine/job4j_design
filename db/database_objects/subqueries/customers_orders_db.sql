CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Ариана', 'Ламберти', 18, 'Италия'),
('Леонор', 'де Вильфранш', 35, 'Франция'),
('Эльвира', 'Монталбан', 31, 'Испания'),
('Михаэль', 'фон Хоэнберг', 42, 'Германия'),
('Исабель', 'Ривера', 18, 'Мексика'),
('Джованна', 'Бенвенути', 33, 'Италия'),
('Рафаэль', 'Ортега', 39, 'Испания'),
('Елена', 'Сорокина', 18, 'Россия'),
('Амели', 'Сержи', 29, 'Франция'),
('Лиана', 'Сео', 32, 'Южная Корея');

select * from customers
where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) values
(7, 1),
(0, 2),
(0, 3),
(0, 4),
(6, 5),
(4, 6),
(1, 7),
(8, 8),
(3, 9),
(5, 10);

select * from customers
where customers.id not in (
			    select customer_id
			    from orders
			    where amount > 0
			    );
