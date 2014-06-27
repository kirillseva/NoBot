# --- First database schema

# --- !Ups

create table user (
  email                     varchar(255) not null primary key,
  name                      varchar(255) not null,
  password                  varchar(255) not null
);

create table grids (
  id                        int(255) not null primary key,
  task                      varchar(255),
  email                     varchar(255) not null,
  layout                    varchar(255) not null
);


# --- !Downs

drop table if exists grids;
drop table if exists user;
