CREATE TABLE user(
id bigint NOT NULL auto_increment primary key,
username varchar(255) not null Unique,
password varchar(255) not null,
roles varchar(20) default ('USER'),
status varchar(20) default ('ACTIVE')
);

CREATE TABLE user_role(
user_id bigint,
role varchar(40),
foreign key (user_id) references user(id)
);


INSERT into user (id, username, password, roles, status)
values (1, 'admin', '$2y$12$M1HSwadozKcg07kWLmO7IeooeRpmS9appi4U0glLhrINduzU8IvfG' , 'ADMIN', 'ACTIVE') ;

INSERT into user (id, username, password, roles, status)
values (2, 'user', '$2y$12$4XHy4yJHDoaYAAqRTm9lbuk8PBauh4ZoHKakZslXysB7nxsz4hMme' , 'USER', 'BANNED') ;


INSERT into user_role (user_id, role) values (1,'ADMIN');