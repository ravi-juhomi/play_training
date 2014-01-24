# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table energy_link_registration (
  id                        bigint not null,
  full_name                 varchar(255),
  company                   varchar(255),
  email                     varchar(255),
  pass_word                 varchar(255),
  constraint pk_energy_link_registration primary key (id))
;

create sequence energy_link_registration_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists energy_link_registration;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists energy_link_registration_seq;

