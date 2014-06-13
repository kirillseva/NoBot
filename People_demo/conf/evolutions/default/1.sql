# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table people_model (
  id                        integer auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  status                    varchar(255),
  office                    integer,
  phone                     varchar(255),
  email                     varchar(255),
  additional_info           varchar(255),
  constraint pk_people_model primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table people_model;

SET FOREIGN_KEY_CHECKS=1;

