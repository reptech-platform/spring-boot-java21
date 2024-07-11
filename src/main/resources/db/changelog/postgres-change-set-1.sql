--liquibase formatted sql

--changeset person:1
create table trippin.person (
  user_name varchar(255) not null, 
  age smallint, 
  date_of_birth date, 
  favorite_feature smallint check (
    favorite_feature between 0 
    and 3
  ), 
  features smallint array, 
  first_name varchar(255), 
  friend varchar(255), 
  gender varchar(255) check (
    gender in ('MALE', 'FEMALE', 'UNKNOWN')
  ), 
  "home_address_address" varchar(255), 
  "home_address_city_country_region" varchar(255), 
  "home_address_city_name" varchar(255), 
  "home_address_city_region" varchar(255), 
  "home_address_code" integer, 
  income numeric(38, 2), 
  last_name varchar(255), 
  middle_name varchar(255), 
  photo oid, 
  primary key (user_name)
);

--changeset person_address_info:2
create table trippin.person_address_info (
  user_name varchar(255) not null, 
  address varchar(255), 
  "country_region" varchar(255), 
  "location_name" varchar(255), 
  "region" varchar(255), 
  code integer
);

--changeset person_email:3
create table trippin.person_email (
  "id" serial not null, 
  "emails" varchar(255), 
  "user_name" varchar(255), 
  primary key ("id")
);

--changeset person_friend:4
create table trippin.person_friend (user_name varchar(255) not null, friend varchar(255) not null);

--changeset 12345:5
alter table if exists trippin.person add constraint FKe03hxea5p72eer78gm8bjkfdu foreign key (friend) references trippin.person;

--changeset 54321:6
alter table if exists trippin.person_address_info add constraint FK2yibnpffvmpjnjfig4r6cdjao foreign key (user_name) references trippin.person;

--changeset 23451:7
alter table if exists trippin.person_email add constraint FKahukv8v26n42kkshq2nfv6pct foreign key (user_name) references trippin.person;

--changeset 15432:8
alter table if exists trippin.person_friend add constraint FKl8ud6j4war0ddhpg8rtml168f foreign key (friend) references trippin.person;

--changeset 34512:9
alter table if exists trippin.person_friend add constraint FKgeot4ffrfqt6iwobjf746qv9b foreign key (user_name) references trippin.person;