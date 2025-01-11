create table departments (
id serial primary key,
name varchar(255)
);

create table employees (
id serial primary key,
name varchar(255),
department_id int references departments (id)
);

insert into departments (name) values
('HR'), ('Finance'), ('IT'), ('Marketing'), ('Sales');

insert into employees (name, department_id) values
('Alice', 3),
('Diana', 4),
('Emma', 4),
('Fiona', 1),
('Helen', 2),
('Olivia', 4),
('Milana', 1),
('Sophia', 1),
('Jane', 1);

select d.name as "Department", e.name as "Employee"
from departments as d
left outer join employees as e on d.id = e.department_id;

select d.name as "Department", e.name as "Employee"
from employees as e
right outer join departments as d on d.id = e.department_id;

select *
from employees as e
full outer join departments as d on d.id = e.department_id;

select d.name as "Department", e.name as "Employee"
from departments as d
cross join employees as e;

select d.name as "Department", e.name as "Employee"
from departments as d
left outer join employees as e on d.id = e.department_id
where e.name is null;

select d.name as "Department", e.name as "Employee"
from departments as d
left outer join employees as e on d.id = e.department_id;

select d.name as "Department", e.name as "Employee"
from employees as e
right outer join departments as d on d.id = e.department_id;

select d.name as "Department", e.name as "Employee"
from departments as d
cross join employees as e;
