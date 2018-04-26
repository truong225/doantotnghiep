create database news;
use news;

create table category(
  id int primary key ,
  name nvarchar(50)
);

create table news(
  id int primary key ,
  tittle nvarchar(100),
  image text,
  source text,
  publishDate datetime,
  guid text,
  category_id int,
  foreign key (category_id) references category(id)
);

create table rss(
  id int primary key ,
  name nvarchar(50),
  link text,
  category_id int,
  foreign key (category_id) references category(id)
);

create table user(
  id int primary key ,
  username nvarchar(50),
  password text,
  fullname nvarchar(50),
  email text,
  phone nvarchar(12),
  addDate datetime,
  lastModified datetime,
  description text,
  status nvarchar(10)
);

create table favorite(
  id int primary key ,
  user_id int,
  category_id int,
  foreign key (user_id) references user(id),
  foreign key (category_id) references category(id)
)