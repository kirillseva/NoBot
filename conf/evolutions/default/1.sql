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
