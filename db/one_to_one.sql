create table student(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    course int
);

create table student_id_card(
    id serial primary key,
    number int,
    student_id int references student(id) unique
);

insert into student(name, surname, course) values ('Maxsim', 'Kuzmin', 4);
insert into student(name, surname, course) values ('Mikhail', 'Kuzmin', 1);
insert into student_id_card(number, student_id) values (101, 1), (202, 2);

select s.name, s.surname, s.course, s_id.number as card_number
from student_id_card as s_id
inner join student as s on s.id = s_id.student_id;