alter table post add user_id bigint;
alter table post add foreign key (user_id) references user(id);