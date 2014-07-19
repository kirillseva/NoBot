# --- First database schema

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
 id 			int not null auto_increment,
 first_name		varchar(255) not null,
 last_name 		varchar(255) not null,
 status			varchar(255),
 office			int,
 phone			varchar(255),
 email			varchar(255),
 additional_info 	varchar(255),
 primary key (id)
);

insert into people (first_name, last_name, status, office, phone, email, additional_info) values ("Jane", "Miller", "admnistrator", "267", "(412) 268-4359", "jmiller@cmu.edu", "Jane Dixon Miller is the Programs Manager for the Master of Software Engineering Professional programs. She earned a Bachelor of Arts in English Literature and a Master in Business Administration from Seton Hill University.");


# --- !Downs
drop table if exists widget_layout;
drop table if exists location;
drop table if exists widget;
drop table if exists layout;
drop table if exists user;
drop table if exists people;