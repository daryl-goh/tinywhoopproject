CREATE USER 'tinywhop'@'localhost' IDENTIFIED BY 'Password@123456';

GRANT ALL PRIVILEGES ON *.* TO 'tinywhop'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;

drop database if exists tinywhopdb;

create database tinywhopdb;

use tinywhopdb;

CREATE table race (
	race_id int not null,
    race_name varchar(128) not null,
    primary key (race_id)
);

CREATE table pilot (
	pilot_id char(8) not null,
    pilot_name varchar(128) not null,
    drone_name varchar(128) not null,
    primary key (pilot_id)
);

CREATE table race_details (
	id int auto_increment not null,
	race_id int not null,
    pilot_id char(8) not null,
    closing_date datetime,
    number_of_laps int,
    primary key (id),
    foreign key (race_id) references race (race_id)
);

create table laps (
	lap_id int auto_increment not null,
    pilot_id char(8) not null,
    race_id int not null,
    time decimal(4,2),
    primary key (lap_id),
    foreign key (pilot_id) references pilot (pilot_id),
    foreign key (race_id) references race_details (race_id)
);

-- DESCRIBE RACE TABLE
DESC race;

-- INSERT RACE TABLE
INSERT INTO race (race_id, race_name) VALUES (1, 'Mario Race');
INSERT INTO race (race_id, race_name) VALUES (2, 'Luigi Race');
INSERT INTO race (race_id, race_name) VALUES (3, 'Bowser Race');

-- SELECT RACE TABLE
SELECT * FROM race;

-- DESCRIBE PILOT TABLE
DESC pilot;

-- INSERT INTO PILOT
INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES ('1234567A', 'DARYL GOH', 'HACKERDRONE_DG');
INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES ('1234567B', 'YIZHUN SIM', 'HACKERDRONE_YZ');
INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES ('1234567C', 'DARREN', 'HACKERDRONE_DAR');

-- SELECT PILOT
SELECT * FROM pilot;

-- DESCRIBE RACE_DETAILS TABLE
DESC race_details;

-- INSERT INTO RACE DETAILS

-- Daryl
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (1, '1234567A', NOW() + INTERVAL 10 DAY, 8);
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (2, '1234567A', NOW() + INTERVAL 20 DAY, 8);
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (3, '1234567A',  NOW() + INTERVAL 30 DAY, 8);

-- Yizhun
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (2, '1234567B', NOW() + INTERVAL 20 DAY, 8);
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (3, '1234567B', NOW() + INTERVAL 30 DAY, 8);

-- Darren
INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (3, '1234567C', NOW() + INTERVAL 30 DAY, 8);

-- SELECT RACE COURSE -> List of Race Course
SELECT * FROM race_details;

-- DESCRIBE LAPS TABLE
DESC laps;

-- INSERT INTO LAPS

-- Daryl
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '10.10');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '9.15');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '9.25');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '9.35');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '9.55');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '10.20');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '10.30');
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567A', 1, '9.33');

-- Yizhun
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567B', 1, '10.30');

-- DARREN
INSERT INTO laps (pilot_id, race_id, time) VALUES ('1234567C', 2, '10.19');

-- SELECT LAPS
SELECT * FROM laps;

-- SQL_RETRIEVE_ALL_RACE_COURSES
SELECT DISTINCT(r.race_name)
	FROM race_details rd, race r
    WHERE rd.race_id = r.race_id;

-- SQL_RETRIEVE_RACE_PARTICAPTION_PILOTS
SELECT r.race_name, p.pilot_name
	FROM race_details rd
    LEFT JOIN race r
    ON rd.race_id = r.race_id
    LEFT JOIN pilot p
    on rd.pilot_id = p.pilot_id
	WHERE r.race_id = 3;

-- SQL_RETRIEVE_LAPTIMINGS_OF_PILOT_PARTICAPTION_BY_RACE
SELECT p.pilot_name, l.lap_id, l.time
	FROM laps l
    LEFT JOIN race r
		on l.race_id  = r.race_id
	LEFT JOIN pilot p
		on l.pilot_id = p.pilot_id
	WHERE l.pilot_id = '1234567C' and l.race_id = 2;
	


----------------------

-- DROP ALL TABLES

DROP TABLE laps;
DROP TABLE pilot;
DROP TABLE race_details;
DROP TABLE race;


