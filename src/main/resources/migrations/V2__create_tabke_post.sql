CREATE TABLE post(
id bigint auto_increment primary key,
title_workout varchar(255) not null,
workout_day varchar(255) not null,
description_workout varchar(255) not null,
duration_of_training int
);