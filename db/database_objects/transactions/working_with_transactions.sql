create table users
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email TEXT NOT NULL,
    age  INT NOT NULL
);

insert into users (name, email, age)
values
  ('Alice', 'alice@example.com', 18),
  ('Bob', 'bob@example.com', 30),
  ('Charlie', 'charlie@example.com', 35),
  ('David', 'david@example.com', 40),
  ('Emma', 'emma@example.com', 18);

-- Запустим транзакцию:
begin;

-- Выполним операцию INSERT:
insert into users (name, email, age) values ('Tom', 'tom@example.com', 20);

-- Зафиксируем изменения:
commit;

-- Выполним проверку состояния таблицы:
select * from users;

-- Начнем новую транзакцию:
begin;

-- Удалим таблицу из БД:
drop table users;

-- Выполним откат изменений с помощью ROLLBACK:
rollback;

-- Выполним выборку данных из таблицы users:
select * from users;

-- Начнем новую транзакцию:
begin;

-- Выполним операцию INSERT:
insert into users (name, email, age) values ('Alex', 'alex@example.com', 25);

-- Добавим первую точку сохранения в транзакцию:
savepoint savepoint1;

-- Выполним операции DELETE и UPDATE:
delete from users where age < 20;
update users set email = 'bob@gmail.com' where id = 2;

-- Выполним выборку данных из таблицы users:
select * from users;

-- Добавим вторую точку сохранения в транзакцию:
savepoint savepoint2;

-- Выполним операцию INSERT:
insert into users (name, email, age) values ('Elena', 'elena@example.com', 18);

-- Выполним выборку данных из таблицы users:
select * from users;

-- Добавим третью точку сохранения в транзакцию:
savepoint savepoint3;

-- Выполним ROLLBACK до второй точки сохранения:
rollback to savepoint2;

-- Выполним выборку данных из таблицы users:
select * from users;

-- Выполним ROLLBACK до третьей точки сохранения (ОШИБКА: точка сохранения "savepoint3" не существует):
rollback to savepoint3;

-- Выполним ROLLBACK до первой точки сохранения:
rollback to savepoint1;

-- Выполним выборку данных из таблицы users:
select * from users;

-- Удалим точки сохранения:
release savepoint savepoint1;

-- Зафиксируем изменения:
commit;