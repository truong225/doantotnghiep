-- Create database
drop database if exists newspaper;
create database newspaper CHARACTER SET utf8 COLLATE utf8_general_ci;
use newspaper;

-- Create table Category
create table category(
	id int not null auto_increment,
  name text not null,
  primary key(id)
);

CREATE index idx_category on category(id);


-- Create table newspaper
create table news(
	id int not null auto_increment,
	title text not null,
	image text,
	description text not null,
	content text not null,
	releash_date text,
	source text not null,
	category_id int not null,
	primary key(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
);

create index idx_news on news(id);


-- Create table User
CREATE TABLE user(
  id int not null auto_increment,
  name nvarchar(30) not null,
  password nvarchar(256) not null,
  PRIMARY KEY (id)
)


select * from news;