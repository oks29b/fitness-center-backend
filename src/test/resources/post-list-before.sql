TRUNCATE table post;

insert post(id, title_workout, workout_day, description_workout, duration_of_training, user_id) values
       (1, 'first','2022-11-01','my-text', 0, 1),
       (2, 'sec','2022-11-01', 'my-more', 0, 1),
       (3, 'third', '2022-11-01', 'my-text', 0, 1),
       (4, 'fourth', '2022-11-01', 'another', 0, 1);

alter table post AUTO_INCREMENT 10;
