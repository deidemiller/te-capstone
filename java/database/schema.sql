BEGIN TRANSACTION;

DROP TABLE IF EXISTS pothole;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE pothole (
	pothole_id serial,
	date_reported date NOT NULL,
	latitude decimal(10,6) NOT NULL,
	longitude decimal(10,6) NOT NULL,
	image_location varchar(256),
	cross_street_1 varchar(256) NOT NULL,
	cross_street_2 varchar(256) NOT NULL,
	contact_name varchar(128) NOT NULL,
	contact_email varchar(128) NOT NULL,
	contact_phone varchar(15) NOT NULL,
	pending boolean,
	severity smallint NOT NULL,
	repair_status varchar(15) NOT NULL,
	repair_date date,
	CONSTRAINT pk_pothole PRIMARY KEY (pothole_id));

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-09', 39.938010, -82.992751, 'https://ik.imagekit.io/q6zdnk39roo/ian-taylor-cZokzzlC3KM-unsplash_UPdf2JpA8.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534868646', '4th St', 'E Gates St', 'John Doe', 'john_doe@aol.com', '555-555-5555', false, 8, 'unscheduled');

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-09', 40.061212, -83.100036, 'https://ik.imagekit.io/q6zdnk39roo/matt-hoffman--OOiAy2lBZc-unsplash__1__VPw8VC-c7.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534868036', 'Henderson Rd', 'Horizons Dr', 'Jane Doe', 'jane_doe@gmail.com', '555-555-5555', false, 7, 'scheduled');

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-09', 39.987221, -83.016627, 'https://ik.imagekit.io/q6zdnk39roo/liam-riby-j8h2_9UDqrM-unsplash_bAGSkwa3F.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534861081', 'Michigan Ave', '5th Ave', 'Smithers Smith', 'smither.smith@burnspower.com', '555-555-5555', false, 3, 'scheduled');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-13', 39.958051, -82.970876, 'S Ohio Ave', 'E Main St', 'Doug Hopkins', 'doug@aol.com', '555-555-5555', true, 5, 'unscheduled');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-13', 39.967878, -82.988570, 'E Spring St', 'N 9th St', 'Isaac Brock', 'ikemm@gmail.com', '555-555-5555', true, 3, 'unscheduled');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status) VALUES ('2022-04-13', 39.969350, -82.995998, 'N 5th St', 'Mt Vernon Ave', 'Justin Vernon', 'foremma@gmail.com', '555-555-5555', true, 7, 'unscheduled');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, repair_date) VALUES ('2022-04-01', 39.985646, -82.968110, 'E 5th Ave', 'N 22nd St', 'Will Wilson', 'wwilson@gmail.com', '444-444-4444', false, 7, 'completed', '2022-04-08');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, repair_date) VALUES ('2022-03-01', 39.994001, -82.987847, 'E 11th Ave', 'Essex Ave', 'Dave Pearson', 'dpdanger@gmail.com', '555-555-5555', false, 3, 'completed', '2022-03-18');


COMMIT TRANSACTION;
