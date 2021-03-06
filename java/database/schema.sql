BEGIN TRANSACTION;

DROP TABLE IF EXISTS pothole;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS employee;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;



CREATE TABLE employee (
	employee_id serial,
	first_name varchar (64) NOT NULL,
	last_name varchar (64) NOT NULL,
	title varchar (32) NOT NULL,
	image_url varchar(128),
	CONSTRAINT PK_employee PRIMARY KEY (employee_id)
);

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
	severity varchar(16) NOT NULL,
	repair_status varchar(15) NOT NULL,
	repair_date date,
	inspected boolean,
	showDetails boolean,
	employee_id int,
	CONSTRAINT PK_pothole PRIMARY KEY (pothole_id),
	CONSTRAINT FK_pothole_employee_id FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('Dustin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('Katherine','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('John','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO employee(first_name, last_name, title, image_url) VALUES ('Steve', 'Carmichael', 'Bossman', 'https://ik.imagekit.io/q6zdnk39roo/Untitled_design__6__m1ZwdDvEk.png?ik-sdk-version=javascript-1.4.3&updatedAt=1650566382651');
INSERT INTO employee(first_name, last_name, title, image_url) VALUES ('Matt', 'Eland', 'Road Service Specialist', 'https://ik.imagekit.io/q6zdnk39roo/Untitled_design__7__eQP8OxOmo.png?ik-sdk-version=javascript-1.4.3&updatedAt=1650566394318');
INSERT INTO employee(first_name, last_name, title, image_url) VALUES ('John', 'Fulton', 'Road Service Specialist', 'https://ik.imagekit.io/q6zdnk39roo/Untitled_design_aEz66kcTB.png?ik-sdk-version=javascript-1.4.3&updatedAt=1650564443766');
INSERT INTO employee(first_name, last_name, title, image_url) VALUES ('Brian', 'Lauvray', 'Road Service Specialist', 'https://ik.imagekit.io/q6zdnk39roo/Untitled_design__5__Dx6oDgyi-.png?ik-sdk-version=javascript-1.4.3&updatedAt=1650566224440');

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails) VALUES ('2022-04-09', 39.938010, -82.992751, 'https://ik.imagekit.io/q6zdnk39roo/ian-taylor-cZokzzlC3KM-unsplash_UPdf2JpA8.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534868646', '4th St', 'E Gates St', 'John Doe', 'john_doe@aol.com', '555-555-5555', false, 'low', 'unscheduled', false, false);

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails, employee_id, repair_date) VALUES ('2022-04-09', 40.061212, -83.100036, 'https://ik.imagekit.io/q6zdnk39roo/matt-hoffman--OOiAy2lBZc-unsplash__1__VPw8VC-c7.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534868036', 'Henderson Rd', 'Horizons Dr', 'Jane Doe', 'jane_doe@gmail.com', '555-555-5555', false, 'moderate', 'scheduled', true, false, 2, '2022-04-26');

INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails, employee_id, repair_date) VALUES ('2022-04-09', 39.987221, -83.016627, 'https://ik.imagekit.io/q6zdnk39roo/liam-riby-j8h2_9UDqrM-unsplash_bAGSkwa3F.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1649534861081', 'Michigan Ave', '5th Ave', 'Smithers Smith', 'smither.smith@burnspower.com', '555-555-5555', false, 'high', 'scheduled', false, false, 3, '2022-04-26');

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails) VALUES ('2022-04-13', 39.958051, -82.970876, 'S Ohio Ave', 'E Main St', 'Doug Hopkins', 'doug@aol.com', '555-555-5555', true, 'moderate', 'unscheduled', false, false);

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails) VALUES ('2022-04-13', 39.967878, -82.988570, 'E Spring St', 'N 9th St', 'Isaac Brock', 'ikemm@gmail.com', '555-555-5555', true, 'low', 'unscheduled', false, false);

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, inspected, showDetails) VALUES ('2022-04-13', 39.969350, -82.995998, 'N 5th St', 'Mt Vernon Ave', 'Justin Vernon', 'foremma@gmail.com', '555-555-5555', true, 'high', 'unscheduled', false, false);

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, repair_date, inspected, showDetails, employee_id) VALUES ('2022-04-01', 39.985646, -82.968110, 'E 5th Ave', 'N 22nd St', 'Will Wilson', 'wwilson@gmail.com', '444-444-4444', false, 'moderate', 'completed', '2022-04-08', true, false, 4);

INSERT INTO pothole (date_reported, latitude, longitude, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, pending, severity, repair_status, repair_date, inspected, showDetails, employee_id) VALUES ('2022-03-01', 39.994001, -82.987847, 'E 11th Ave', 'Essex Ave', 'Dave Pearson', 'dpdanger@gmail.com', '555-555-5555', false, 'low', 'completed', '2022-03-18', true, false, 2);


COMMIT TRANSACTION;
