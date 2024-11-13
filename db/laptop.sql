create table laptop(
	id serial primary key,
	name text,
	model varchar(255),
	price int,
	is_gaming bool
);
insert into laptop(name, model, price, is_gaming) values('asus', 'rog', 1500, true);
select * from laptop;
update laptop set price = 2000;
select * from laptop;
delete from laptop;