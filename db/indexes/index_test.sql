-- Таблица perf_test: тестируем производительность работы с индексами и выборками
create table perf_test(
	id int,
	reason text,
	annotation text
);

-- Заполняем таблицу 1 000 000 случайных записей:
insert into perf_test(id, reason, annotation)
select s.id, md5(random()::text), null -- Случайная строка в поле reason, annotation пока NULL
from generate_series(1, 1000000) as s(id) -- Генерация 1 000 000 идентификаторов
order by random(); -- Перемешиваем записи для дополнительной энтропии

-- Обновляем поле annotation случайными строками в верхнем регистре:
update perf_test
set annotation = upper(md5(random()::text));

-- Просматриваем первые 10 записей для проверки данных:
select * from perf_test
limit 10;

-- Выборка без индекса: выбор записи по id (последовательный поиск)
explain
select *
from perf_test
where id = 690000;

-- Создаем индекс по id:
create index idx_perf_test_id on perf_test(id); -- B-tree (по умолчанию)

-- Выборка с индексом:
explain analyze
select *
from perf_test
where id = 690000;

-- Запрос на выборку записей с условиями по двум столбцам reason и annotation
explain analyze
select *
from perf_test
where reason like 'ac%' and annotation like 'DC%';

-- Создаем составной индекс для ускорения выборки по двум полям reason и annotation
create index idx_perf_test_reason_annotation on perf_test(reason, annotation);

-- Выборка с индексом
select *
from perf_test
where reason like 'ac%' and annotation like 'DC%';

-- Проверяем ускорение поиска только по столбцу reason (индексный поиск)
explain
select *
from perf_test
where reason like 'ac%';

-- Проверяем поиск только по столбцу annotation (последовательный поиск)
explain
select *
from perf_test
where annotation like 'DC%';

-- Создаем отдельный индекс для поля annotation, чтобы ускорить выборку
create index idx_perf_test_annotation on perf_test(annotation);

-- Выборка с индексом
select *
from perf_test
where annotation like 'DC%';

-- Дополнительные команды для администрирования и анализа:

-- Удаление всех записей в таблице:
-- delete from perf_test;

-- Удаление таблицы:
-- drop table perf_test;

-- Сбор статистики для оптимизатора запросов:
-- analyze perf_test;

-- Удаление индекса:
-- drop index idx_perf_test_reason_annotation;

-- Переименование индекса
-- alter index idx_perf_test_id rename to idx_perf_test_id_desc;

-- Просмотр доступных методов индексации в текущей базе данных:
-- SELECT amname FROM pg_am;
-- Например, btree (по умолчанию), hash, gin, gist.

-- Примечание:
-- Индексы на столбцы, по которым не выполняются частые выборки или сортировки, могут занимать лишнее место и замедлять вставки/обновления.
-- Рекомендуется создавать индексы только на те столбцы, которые действительно участвуют в фильтрации или сортировке.

-- Создание новой базы данных с указанной кодировкой, локалью и шаблоном
CREATE DATABASE new_database
WITH ENCODING 'UTF8'  -- Устанавливаем кодировку UTF8
LC_COLLATE 'C'        -- Локаль для сортировки
LC_CTYPE 'C'          -- Локаль для классификации символов
TEMPLATE template0;   -- Используем пустой шаблон базы данных

