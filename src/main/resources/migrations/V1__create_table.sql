CREATE TABLE user(
id int NOT NULL auto_increment primary key,
username varchar(255) not null Unique,
password varchar(255) not null,
roles varchar(20) default ('USER'),
status varchar(20) default ('ACTIVE')
);

CREATE TABLE role(
id int PRIMARY KEY AUTO_INCREMENT,
nameRole varchar(255) not null
);

CREATE TABLE userRole(
userId int,
roleId int,
foreign key (roleId) references role(id),
foreign key (userId) references user(id)
);


INSERT into user (id, username, password, roles, status)
values (1, 'admin', '$2y$12$M1HSwadozKcg07kWLmO7IeooeRpmS9appi4U0glLhrINduzU8IvfG' , 'ADMIN', 'ACTIVE') ;

INSERT into user (id, username, password, roles, status)
values (2, 'user', '$2y$12$4XHy4yJHDoaYAAqRTm9lbuk8PBauh4ZoHKakZslXysB7nxsz4hMme' , 'USER', 'BANNED') ;


INSERT into role(nameRole) values ('ADMIN');
INSERT into role(nameRole) values ('USER');

CREATE TABLE status(
id int PRIMARY KEY AUTO_INCREMENT,
nameStatus varchar(255) not null
);

INSERT into status(nameStatus) values ('ACTIVE');
INSERT into status(nameStatus) values ('BANNED');