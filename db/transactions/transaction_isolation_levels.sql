CREATE TABLE users
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(20),
    age  INT
);

INSERT INTO users (name, email, age)
VALUES
  ('Alice', 'alice@example.com', 18),
  ('Bob', 'bob@example.com', 30),
  ('Charlie', 'charlie@example.com', 35),
  ('David', 'david@example.com', 40),
  ('Emma', 'emma@example.com', 18);

-- ЧТЕНИЕ ПОДТВЕРЖДЕННЫХ ДАННЫХ(READ COMMITTED).

-- Начинаем две параллельные транзакции:

-- Первая транзакция.
begin transaction;

-- Вторая транзакция
begin transaction;

-- Проверяем какая информация имеется в начале после каждой транзакции.
select * from users;

-- Выполним операции INSERT, DELETE, UPDATE в первой транзакции.
insert into users (name, email, age) VALUES ('Tom', 'tom@example.com', 20);
delete from users where age < 20;
update users set email = 'bob@gmail.com' where id = 2;

-- Проверим, что в этом случае видит вторая транзакция (без изменений).
select * from users;

-- Зафиксируем все изменения в первой транзакции.
commit;

-- Проверим, что в этом случае видит вторая транзакция (изменения есть).
select * from users;

-- Очистим таблицу
delete from users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (name, email, age)
VALUES
  ('Alice', 'alice@example.com', 18),
  ('Bob', 'bob@example.com', 30),
  ('Charlie', 'charlie@example.com', 35),
  ('David', 'david@example.com', 40),
  ('Emma', 'emma@example.com', 18);

-- ПОВТОРЯЮЩЕЕСЯ ЧТЕНИЕ(REPEATABLE READ).

-- Начинаем две параллельные транзакции и установим уровень изолированности:

-- Для первой транзакции.
begin transaction isolation level repeatable read;

-- Для второй транзакции.
begin transaction isolation level repeatable read;

-- Проверяем какая информация имеется в начале после каждой транзакции.
select * from users;

-- Выполним операции INSERT, DELETE, UPDATE в первой транзакции.
insert into users (name, email, age) VALUES ('Tom', 'tom@example.com', 20);
delete from users where age < 20;
update users set email = 'bob@gmail.com' where id = 2;

-- Выполним операцию UPDATE во второй транзакции(lock).
update users set email = 'bob@gmail.com' where id = 2;

-- откат в первой транзакции (при этом во вторая зашла операция update)
rollback;
-- откат во второй транзакции (для повторения эксперимента уже с коммитом)
rollback;

-- Для первой транзакции.
begin transaction isolation level repeatable read;

-- Для второй транзакции.
begin transaction isolation level repeatable read;

-- Выполним операции INSERT, DELETE, UPDATE в первой транзакции.
insert into users (name, email, age) VALUES ('Tom', 'tom@example.com', 20);
delete from users where age < 20;
update users set email = 'bob@gmail.com' where id = 2;

-- Выполним операцию UPDATE во второй транзакции(lock).
update users set email = 'bob@gmail.com' where id = 2;

-- Зафиксируем  изменения в первой транзакции (при этом во второй транзакции - не удалось сериализовать доступ из-за параллельного изменения)
commit;

delete from users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (name, email, age)
VALUES
  ('Alice', 'alice@example.com', 18),
  ('Bob', 'bob@example.com', 30),
  ('Charlie', 'charlie@example.com', 35),
  ('David', 'david@example.com', 40),
  ('Emma', 'emma@example.com', 18);

-- SERIALIZABLE

-- Начинаем две параллельные транзакции и установим уровень изолированности:

-- Для первой транзакции.
begin transaction isolation level serializable;

-- Для второй транзакции.
begin transaction isolation level serializable;

-- Проверяем какая информация имеется в начале после каждой транзакции.
select * from users;

-- В первой транзакции находим средний возраст по столбцу age и добавим новую строку.
select round(avg(age), 2) from users;

INSERT INTO users (name, email, age) VALUES ('Alex', 'alex@example.com', 32);

-- Во втрой транзакции находим средний возраст по столбцу age и обновим данные.
select round(avg(age), 2) from users;

update users set email = 'emma@gmail.com' where id = 5;

-- Зафиксируем все изменения в первой транзакции.
commit;

-- Зафиксируем все изменения во второй транзакции (ОШИБКА: не удалось сериализовать доступ из-за зависимостей чтения/записи между транзакциями).
commit;

delete from users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (name, email, age)
VALUES
  ('Alice', 'alice@example.com', 18),
  ('Bob', 'bob@example.com', 30),
  ('Charlie', 'charlie@example.com', 35),
  ('David', 'david@example.com', 40),
  ('Emma', 'emma@example.com', 18);