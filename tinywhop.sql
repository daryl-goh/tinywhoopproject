CREATE USER 'tinywhop'@'localhost' IDENTIFIED BY 'Password@123456';

GRANT ALL PRIVILEGES ON *.* TO 'tinywhop'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;

drop database if exists tinywhopdb;

create database tinywhopdb;

use tinywhopdb;

CREATE table race_course (
	race_course_id int auto_increment not null,
    pilot_id char(8) not null,
    race_name varchar(128) not null,
    closing_date datetime,
    number_of_laps int,
    primary key (race_course_id)
);

CREATE table pilot (
	pilot_id char(8) not null,
    race_course_id int not null,
    pilot_name varchar(128) not null,
    drone_name varchar(128) not null,
    primary key (pilot_id),
    constraint fk_race_course_id
		foreign key (race_course_id) references race_course(race_course_id)
);

create table laps (
	lap_id int auto_increment not null,
    pilot_id char(8) not null,
    race_course_id int not null,
    time decimal(2,2),
    primary key (lap_id),
    foreign key (pilot_id) references pilot (pilot_id),
    foreign key (race_course_id) references race_course (race_course_id)
);