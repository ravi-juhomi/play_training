# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_registration (
  id                        bigint not null,
  full_name                 varchar(255),
  company                   varchar(255),
  email                     varchar(255),
  pass_word                 varchar(255),
  constraint pk_user_registration primary key (id))
;

create sequence user_registration_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user_registration;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_registration_seq;

