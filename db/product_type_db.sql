create table type (
id serial primary key,
name varchar(255) NOT NULL
);

create table product (
id serial primary key,
name varchar(255) NOT NULL,
type_id  int references type(id),
expired_date date NOT NULL,
price float NOT NULL
);

insert into type (name) values
('Сыр'),
('Молоко'),
('Мясо'),
('Рыба'),
('Хлеб'),
('Мороженое'),
('Орехи');

insert into product (name, type_id, expired_date, price) values
('Российский', 1, '2024-12-31', 200.00),
('Пармезан', 1, '2025-01-15', 350.00),
('Чеддер', 1, '2024-12-10', 300.00),
('Бри', 1, '2024-01-10', 320.00),
('Гауда', 1, '2024-12-11', 250.00),
('Фета', 1, '2024-12-13', 270.00),
('Камамбер', 1, '2024-10-20', 330.00),
('Моцарелла', 1, '2024-12-22', 280.00),
('Эдам', 1, '2025-01-15', 240.00),
('Дор Блю', 1, '2024-11-12', 360.00),
('Пекорино', 1, '2024-12-12', 310.00),
('Грюйер', 1, '2025-01-20', 400.00),
('Маасдам', 1, '2024-12-08', 290.00),
('Тильзитер', 1, '2024-12-05', 260.00),
('Адыгейский', 1, '2025-01-15', 220.00),
('Топленое молоко', 2, '2024-12-20', 120.00),
('Цельное молоко', 2, '2024-12-18', 100.00),
('Обезжиренное молоко', 2, '2024-12-22', 110.00),
('Козье молоко', 2, '2024-12-13', 130.00),
('Шоколадное молоко', 2, '2024-11-29', 140.00),
('Свинина', 3, '2024-12-01', 400.00),
('Говядина', 3, '2024-12-05', 450.00),
('Баранина', 3, '2024-12-20', 420.00),
('Курица', 3, '2024-12-15', 300.00),
('Индейка', 3, '2024-12-18', 350.00),
('Семга', 4, '2024-12-22', 500.00),
('Скумбрия', 4, '2024-12-10', 300.00),
('Тунец', 4, '2024-12-04', 450.00),
('Судак', 4, '2024-12-20', 350.00),
('Форель', 4, '2024-12-27', 400.00),
('Минтай', 4, '2024-12-13', 270.00),
('Карась', 4, '2024-12-22', 250.00),
('Щука', 4, '2024-12-20', 370.00),
('Камбала', 4, '2024-12-25', 390.00),
('Осетр', 4, '2024-12-02', 600.00),
('Белуга', 4, '2024-12-29', 700.00),
('Лосось', 4, '2024-12-28', 500.00),
('Палтус', 4, '2024-12-23', 430.00),
('Батон', 5, '2024-12-15', 50.00),
('Ржаной хлеб', 5, '2024-12-18', 40.00),
('Бородинский', 5, '2024-12-22', 60.00),
('Зерновой хлеб', 5, '2024-12-25', 70.00),
('Багет', 5, '2024-12-20', 80.00),
('Хлеб с отрубями', 5, '2024-12-22', 55.00),
('Ванильное мороженое', 6, '2024-12-15', 120.00),
('Шоколадное мороженое', 6, '2024-12-18', 130.00),
('Клубничное мороженое', 6, '2024-12-20', 140.00),
('Карамельное мороженое', 6, '2024-12-25', 150.00),
('Фисташковое мороженое', 6, '2024-12-22', 160.00),
('Пломбир', 6, '2024-12-18', 170.00),
('Фруктовый лёд', 6, '2024-12-20', 110.00),
('Ореховое мороженое', 6, '2024-12-25', 180.00),
('Йогуртовое мороженое', 6, '2024-12-22', 190.00),
('Сливочное мороженое', 6, '2024-12-27', 200.00),
('Банановое мороженое', 6, '2024-12-30', 210.00),
('Манговое мороженое', 6, '2024-12-31', 220.00),
('Черничное мороженое', 6, '2025-01-01', 230.00),
('Кофейное мороженое', 6, '2025-01-02', 240.00),
('Миндаль', 7, '2024-12-30', 500.00),
('Грецкий орех', 7, '2024-12-28', 450.00),
('Фундук', 7, '2024-12-27', 480.00),
('Кешью', 7, '2024-12-26', 530.00),
('Пекан', 7, '2024-12-25', 520.00),
('Кедровые орехи', 7, '2024-12-24', 600.00),
('Бразильский орех', 7, '2024-12-23', 550.00),
('Фисташки', 7, '2024-12-22', 510.00),
('Арахис', 7, '2024-12-21', 400.00),
('Макадамия', 7, '2024-12-20', 700.00);

select p.name as Название, t.name as Категория, p.price as Цена
from product as p
inner join type as t on p.type_id = t.id
where t.name ilike 'Сыр';

select name as Название, price as Цена
from product
where name ilike '%Мороженое%';

select name as "Просроченные продукты"
from product
where current_date > expired_date;

select name as Название, max(price) as Цена
from product
where price = (
				select max(price)
				from product
               )
group by name;

select t.name as Название, count(p.name) as "Количество продуктов"
from product as p
inner join type as t on p.type_id = t.id
group by t.name

select p.name as Название, t.name as Категория
from product as p
inner join type as t on p.type_id = t.id
where t.name ilike 'Сыр' or t.name ilike 'Молоко'
group by p.name, t.name

select t.name as Название, sum(p.price) as "Суммарная цена"
from product as p
inner join type as t on p.type_id = t.id
group by t.name
having count(p.name) < 10

select p.name as Название, t.name as Категория
from product as p
inner join type as t on p.type_id = t.id