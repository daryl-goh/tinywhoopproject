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
    pilot_name varchar(128) not null,
    drone_name varchar(128) not null,
    primary key (pilot_id)
);

create table laps (
	lap_id int auto_increment not null,
    pilot_id char(8) not null,
    race_course_id int not null,
    time decimal(4,2),
    primary key (lap_id),
    foreign key (pilot_id) references pilot (pilot_id),
    foreign key (race_course_id) references race_course (race_course_id)
);

-- DESCRIBE RACE_COURSE TABLE
DESC race_course;

-- INSERT INTO RACE COURSE
INSERT INTO race_course (pilot_id, race_name, closing_date, number_of_laps) VALUES ('1234567A', 'Mario Race', sysdate() + 30, 8);
INSERT INTO race_course (pilot_id, race_name, closing_date, number_of_laps) VALUES ('1234567B', 'Luigi Race', sysdate() + 35, 8);
INSERT INTO race_course (pilot_id, race_name, closing_date, number_of_laps) VALUES ('1234567C', 'Bowser Race', sysdate() + 40, 8);

-- SELECT RACE COURSE
SELECT * FROM race_course;

-- DESCRIBE PILOT TABLE
DESC pilot;

-- INSERT INTO PILOT
INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES ('1234567A', 'DARYL GOH', 'HACKERDRONE_DG');
INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES ('1234567B', 'YIZHUN SIM', 'HACKERDRONE_YZ');

-- SELECT PILOT
SELECT * FROM pilot;

-- DESCRIBE LAPS TABLE
DESC laps;

-- INSERT INTO LAPS
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '10.10');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '9.15');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '9.25');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '9.35');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '9.55');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '10.20');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '10.30');
INSERT INTO laps (pilot_id, race_course_id, time) VALUES ('1234567A', 1, '9.33');

-- SELECT LAPS
SELECT * FROM laps;


-- SELECT RACE PARTICAPTION BY PILOTS
SELECT rc.race_name, p.pilot_id, p.pilot_name
	FROM race_course rc, pilot p 
	WHERE rc.pilot_id = p.pilot_id;

-- SELECT LAP TIMER BY PILOTS
SELECT p.pilot_name, l.lap_id, l.time
	FROM laps l
    LEFT JOIN race_course rc
		on l.race_course_id  = rc.race_course_id
	LEFT JOIN pilot p
		on l.pilot_id = rc.pilot_id
	WHERE l.pilot_id = '1234567A' and l.race_course_id = 1;
	

-- DROP ALL TABLES

-- DROP TABLE race_course;
-- DROP TABLE pilot;
-- DROP TABLE laps;

-- ALTER TABLE laps
-- MODIFY COLUMN TIME DECIMAL(4,2);