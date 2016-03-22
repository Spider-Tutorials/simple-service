CREATE DATABASE IF NOT EXISTS `tutorial`;
use `tutorial`;

CREATE TABLE `product` (
  `name` varchar(20) NOT NULL,
  `description` varchar(100)NOT NULL,
  `producer` varchar(100)
  );
  ALTER TABLE `product`
    ADD PRIMARY KEY (`name`);

insert into product (name, description, producer) values('iphone6', 'apples mobile phone', 'apple');
insert into product (name, description, producer) values('nexus', 'google mobile phone', 'google');


CREATE USER 'tutorial'@'%' IDENTIFIED WITH mysql_native_password AS 'tutorial';GRANT USAGE ON *.* TO 'tutorial'@'%' ;