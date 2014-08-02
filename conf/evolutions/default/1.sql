# --- !Downs
drop table if exists widget_layout;
drop table if exists people;
drop table if exists location;
drop table if exists widget;
drop table if exists layout;
drop table if exists user;
drop table if exists people;

# --- !Ups

create table user (
  email                     varchar(255) not null primary key,
  name                      varchar(255) not null,
  password                  varchar(255) not null
);

create table layout (
  id                        int not null auto_increment,
  task                      varchar(255) not null,
  email                     varchar(255) not null,
  primary key (id),
  unique key taskemail (task, email)
);

create table widget (
  prim_id                   int not null auto_increment,
  id                        varchar(255) not null,
  col                       int not null,
  row                       int not null,
  size_x                    int not null,
  size_y                    int not null,
  primary key (prim_id)
);

create table widget_layout (
  id                        int not null auto_increment,
  widget_id                 int not null,
  layout_id                 int not null,
  primary key (id),
  foreign key (widget_id) references widget(prim_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  foreign key (layout_id) references layout(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

create table location (
  name                      varchar(255) not null primary key,
  x                         int not null,
  y                         int not null
);

create table people (
 id 			                  int not null auto_increment,
 first_name		              varchar(255) not null,
 last_name 		              varchar(255) not null,
 status			                varchar(255),
 office		                	int,
 phone			                varchar(255),
 email			                varchar(255),
 additional_info 	          varchar(255),
 unique key fullname (first_name, last_name),
 primary key (id)
);

insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Jane', 'Miller', 'Associate Director of International Initiatives and Programs Manager', '273', '(412) 268-4359', 'jmiller@cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/miller-jane.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Matthew', 'Bass', 'Associate Director of Software Engineering Professional Programs for Corporate and Alumni Relations', '267', '(412) 268-6163', 'mbass@cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/bass-matt.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('David', 'Garlan', 'Director of Professional Software Engineering Programs', '412-268-5056', 'garlan@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/garlan-david.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('Jonathan', 'Aldrich', 'PhD, University of Washington', '412-268-7278', 'jonathan.aldrich@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/aldrich-jonathan.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('Travis', 'Breaux', 'Assistant Professor of Computer Science', '412-268-7334', 'breaux@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/breaux-travis.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('James', 'Herbsleb', 'Professor', '412 268-8933', 'jdh@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/herbsleb-james%20d..html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Anthony', 'Lattanze', 'Teaching Professor', '278', '(412) 268-4736', 'lattanze@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/lattanze-anthony.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Eduardo', 'Miranda', 'Associate Teaching Professor', '268', '(412) 268-8450', 'mirandae@andrew.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/miranda-eduardo.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Dave', 'Root', 'Associate Teaching Professor', '272', '(412) 268-5198', 'droot@andrew.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/root-dave.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Mel', 'Rosso-Llopart', 'Associate Teaching Professor', '270', '412-268-4629', 'rossollo@cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/rosso%20llopart-mel.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('Mary', 'Shaw', 'Professor', '(412) 268-2589', 'Mary.Shaw@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Faculty/shaw-mary.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('Emanuel', 'Bowes', 'Systems Technician, ISR', '(412) 268-3369', 'peb2+@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/bowes-emanuel.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('Chris', 'Dalansky', 'Systems Manager, ISR', '(412) 268-7138', 'dalansky@cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/chris-dalansky.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('John', 'Lombardo', 'Video Technician, ISR', '(412) 268-5414', 'lumbo65@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/lombardo-john.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Lauren', 'Martinko', 'Masters Program Administrator', '276', '(412) 268-6441', 'laurenma@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/martinko-lauren.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, phone, email, additional_info) values ('TJ', 'Penderville', 'Video Technician, ISR', '(412) 268-5413', 'jtpender@andrew.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/penderville-j.%20t..html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Josh', 'Quicksall', 'Student-Alumni Relations Coordinator', '269', '412-268-8085', 'jquicksa@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/quicksall-josh.html" target="_blank">More info</a>');
insert into people (first_name, last_name, status, office, phone, email, additional_info) values ('Linda', 'Smith', 'Masters Program Administrator', '277', '412-268-5067', 'jtlsmith@cs.cmu.edu', '<a href="http://mse.isri.cmu.edu/software-engineering/Staff/smith-linda.html" target="_blank">More info</a>');

insert into people (first_name, last_name, status, email, additional_info) values ('Ulyana', 'Skladchikova', 'Student', 'uskladch@andrew.cmu.edu', 'Rewyndr');
insert into people (first_name, last_name, status, email, additional_info) values ('Sammy', 'Mudede', 'Student', 'smudede@andrew.cmu.edu', 'CoBot');
insert into people (first_name, last_name, status, email, additional_info) values ('Kirill', 'Sevastyanenko', 'Student', 'seva@cmu.edu', 'CoBot');
insert into people (first_name, last_name, status, email, additional_info) values ('Evgenia', 'Trofimova', 'Student', 'genia@cmu.edu', 'CoBot');
insert into people (first_name, last_name, status, email, additional_info) values ('Denis', 'Anisimov', 'Student', 'danisimo@andrew.cmu.edu', 'Rainbow');
insert into people (first_name, last_name, status, email, additional_info) values ('Anastasia', 'Timoshenko', 'Student', 'atimoshe@andrew.cmu.edu', 'Rainbow');
insert into people (first_name, last_name, status, email, additional_info) values ('Konstantin', 'Urysov', 'Student', 'kurysov@andrew.cmu.edu', 'GiftCards');
insert into people (first_name, last_name, status, email, additional_info) values ('Sudarshan', 'Wadkar', 'Student', 'swadkar@andrew.cmu.edu', 'NIST');
insert into people (first_name, last_name, status, email, additional_info) values ('Deepak', 'Kuttykrishnan', 'Student', 'dkuttykr@andrew.cmu.edu', 'SmartParks');











