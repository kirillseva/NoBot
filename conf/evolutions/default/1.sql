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


# --- !Downs

drop table if exists widget;
drop table if exists layout;
drop table if exists widget_layout;
drop table if exists user;
