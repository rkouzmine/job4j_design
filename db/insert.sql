insert into roles(name) values ('Programmer');
insert into roles(name) values ('Engineer');
insert into roles(name) values ('Inventor');

insert into rules(name) values ('Can write good code');
insert into rules(name) values ('Can manage power systems');
insert into rules(name) values ('Can configure networks');
insert into rules(name) values ('Can perform maintenance');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (1, 3);
insert into roles_rules(roles_id, rules_id) values (2, 2);
insert into roles_rules(roles_id, rules_id) values (2, 4);
insert into roles_rules(roles_id, rules_id) values (3, 4);

insert into categories(name) values ('Bug Fixing');
insert into categories(name) values ('Maintenance');
insert into categories(name) values ('R&D');

insert into states(name) values ('In Progress');
insert into states(name) values ('Completed');

insert into users(name, phone_number, roles_id) values ('Thomas A', '+1-800-628749', 1);
insert into users(name, phone_number, roles_id) values ('Elon M', '+1-555-2020', 2);
insert into users(name, phone_number, roles_id) values ('Tony S', '+1-555-3030', 3);

insert into items(title, users_id, categories_id, states_id) values ('Software Developer', 1, 1, 1);
insert into items(title, users_id, categories_id, states_id) values ('Analyze Power Load Distribution', 2, 2, 2);
insert into items(title, users_id, categories_id, states_id) values ('Come up with something new', 3, 3, 2);

insert into attachs(file, items_id) values ('app_docs.pdf', 1);
insert into attachs(file, items_id) values ('power_load_analysis.xlsx', 2);
insert into attachs(file, items_id) values ('configuration_guide.DWG', 3);

insert into comments(content, items_id) values ('Temet Nosce.', 1);
insert into comments(content, items_id) values ('Power load data needs further verification.', 2);
insert into comments(content, items_id) values ('Configured, ready for testing.', 3);
