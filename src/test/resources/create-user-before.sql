TRUNCATE table user_role;
TRUNCATE table user;

insert into user (id, status, password, username) values (1, 'ACTIVE', '$2a$08$JZTbYqHMJfXJtcVjJOMThOPlxXpk4UWoIfx6MyAhGV2GQZeXGxzki', 'ADMIN');

insert into user (id, status, password, username) values (2, 'ACTIVE', '$2a$08$Lu20boNdqWb06TM7F6FbsOPTVido3jzTW9CL20BAuc6TozvMXonyi', 'max');

insert into user_role(user_id, role) values (1, 'ROLE_USER');
insert into user_role(user_id, role) values (1, 'ROLE_ADMIN');
insert into user_role(user_id, role) values (2, 'ROLE_USER');