create table teens (
id serial primary key,
name varchar(255),
gender varchar(1)
);

insert into teens (name, gender) values
('Алекс', 'M'),
('Вася', 'M'),
('Дима', 'М'),
('Катя', 'Ж'),
('Ваня', 'М'),
('Серый', 'М'),
('Маша', 'Ж'),
('Вова', 'М'),
('Таня', 'Ж');

select
t1.name as Имя,
t2.name as Имя,
(t1.name, t2.name) as "Возможные разнополые пары"
from teens as t1
cross join teens as t2
where (t1.gender = 'M' and t2.gender = 'Ж') or (t1.gender = 'Ж' and t2.gender = 'М');
