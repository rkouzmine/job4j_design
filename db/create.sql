create TABLE roles(
id serial primary key,
name text NOT NULL
);

create TABLE rules(
id serial primary key,
name text NOT NULL
);

create TABLE roles_rules(
roles_id int NOT NULL references roles(id),
rules_id int NOT NULL references rules(id),
PRIMARY KEY (roles_id, rules_id)
);

create TABLE categories(
id serial primary key,
name varchar(255) NOT NULL
);

create TABLE states(
id serial primary key,
name varchar(255) NOT NULL
);

create TABLE users(
id serial primary key,
name varchar(255) NOT NULL,
phone_number VARCHAR(20) NOT NULL,
roles_id int references roles(id)
);

create TABLE items(
id serial primary key,
title text NOT NULL,
users_id int NOT NULL references users(id),
categories_id int NOT NULL references categories(id),
states_id int NOT NULL references states(id)
);

create TABLE attachs(
id serial primary key,
file varchar(255) NOT NULL,
items_id int NOT NULL references items(id)
);

create TABLE comments(
id serial primary key,
content text NOT NULL,
items_id int NOT NULL references items(id)
);
