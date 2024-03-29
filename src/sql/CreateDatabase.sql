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
insert into User(userName, password) values ('administrador', '5395888d441ba59f1bf4f8fb73b950bd');
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
   notified bit default 0,
   companyName varchar(255) null,
   phoneNumber varchar(20) null,
   productCategories varchar(255) null,
   productAmount int null
) ENGINE=MyISAM;

CREATE TABLE Newsletter
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   name varchar(255) NOT NULL,
   subject varchar(255) NOT NULL,
   content varchar(2048) NOT NULL
) ENGINE=MyISAM;
CREATE UNIQUE INDEX name ON Newsletter(name);

CREATE TABLE NewsletterDistribution
(
   Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   newsletterId int NOT NULL references Newsletter(Id),
   contactType varchar(20),
   success int,
   failures int,
   sentDate timestamp not null default now()
) ENGINE=MyISAM;

INSERT INTO Category (id,name) VALUES (1,'billeteras y monederos');
INSERT INTO Category (id,name) VALUES (2,'carteras y bolsos');
INSERT INTO Category (id,name) VALUES (3,'accesorios pelo');
INSERT INTO Category (id,name) VALUES (4,'anillos y aros');
INSERT INTO Category (id,name) VALUES (5,'collares');
INSERT INTO Category (id,name) VALUES (6,'combinados');
INSERT INTO Category (id,name) VALUES (7,'home');
INSERT INTO Category (id,name) VALUES (8,'pañuelos, bufandas y gorros');
INSERT INTO Category (id,name) VALUES (9,'prendas de vestir');
INSERT INTO Category (id,name) VALUES (10,'varios');
