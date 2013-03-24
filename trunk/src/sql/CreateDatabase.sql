create database almajazmin;
CREATE USER '${jdbc.username}'@'%' IDENTIFIED BY '${jdbc.password}';
GRANT ALL PRIVILEGES ON almajazmin.* TO '${jdbc.username}'@'%' WITH GRANT OPTION;
flush privileges;



--ALTER TABLE Product
--drop FOREIGN KEY fk_Product_SmallImage;
--drop table if EXISTS ImageFile;
--drop table if EXISTS Category_Product;
--drop table if EXISTS Product;
--drop table if EXISTS Category;
--drop table if EXISTS UserRole;
--drop table if EXISTS User;
--drop table if EXISTS Contact;

create table User (
	userName varchar(15) not null primary key,
	password varchar(40) not null
) ENGINE=MyISAM;

create table UserRole (
	userName varchar(15) not null REFERENCES User(userName),
	roleName varchar(15) not null,
	primary key (userName, roleName)
) ENGINE=MyISAM;

insert into User(userName, password) values ('admin', '71714fb720dc331d267e582c447baf4b');
insert into User(userName, password) values ('administrador', 'x');
insert into UserRole(userName, roleName) values ('admin', 'admin');
insert into UserRole(userName, roleName) values ('administrador', 'admin');


CREATE TABLE Category
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   name varchar(255) NOT NULL
) ENGINE=MyISAM;
CREATE UNIQUE INDEX name ON Category(name);


CREATE TABLE Product
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   name varchar(30) NULL,
   thumbnailId int null
) ENGINE=MyISAM;


CREATE TABLE ImageFile
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   content longblob,
   fileName varchar(255),
   type varchar(20),
   orderNumber int,
   size varchar(10) NOT NULL,
   fullSizeImageId int null references ImageFile(id),
   productId int null references Product(Id)
) ENGINE=MyISAM;
CREATE INDEX FK43140D57542F4228 ON ImageFile(id);


ALTER TABLE Product
ADD CONSTRAINT fk_Product_Thumbnail
FOREIGN KEY (thumbnailId)
REFERENCES ImageFile(id);


CREATE TABLE Category_Product
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   product_id int NOT NULL references Product(Id),
   category_id int NOT NULL references Category(Id)
) ENGINE=MyISAM;

CREATE INDEX FKE9872EAE14C260D ON Category_Product(category_id);
CREATE INDEX FKE9872EAE3A065DB4 ON Category_Product(product_id);

CREATE TABLE Contact
(
   Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Name varchar(255) NOT NULL,
   contactType varchar(20),
   email varchar(100) NOT NULL,
   comment varchar(512),
   contactDate timestamp not null default now(),
   notified bit default 0
) ENGINE=MyISAM;

