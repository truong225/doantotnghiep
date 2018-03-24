drop database newspaper;

create database newspaper CHARACTER SET utf8 COLLATE utf8_general_ci;
use newspaper;

create table news(
	id int not null auto_increment,
    content text,
    primary key(id)
);

insert into news (content) values("Đài tiếng nói Việt Nam");