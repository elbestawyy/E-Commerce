use `lavana`;


create table `category`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `img` mediumblob default null,
    primary key(`id`)    
);

create table `subcategory`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `img` mediumblob default null,
    `category_id` bigint,
    primary key(`id`),
    constraint foreign key (`category_id`) references `category`(`id`)
);

create table `brand`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `img` mediumblob default null,
    `subcategory_id` bigint not null,
    primary key(`id`),
    constraint foreign key (`subcategory_id`) references `subcategory`(`id`)
);


create table `address`(
	`id` bigint auto_increment not null,
    `street` varchar(55) default null,
    `city` varchar(55) not null,
    `state` varchar(55) not null,
    `country` varchar(55) not null,
    `user` varchar(100) not null,
    primary key(`id`)
);

create table `product`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `description` text default null,
    `quantity` int default null,
    `price` double default null,
    `price_after_discount` double default null,
    `colour` varchar(55) default null,
    `img_cover` mediumblob default null,
    `brand_id` bigint ,
    primary key(`id`),
    constraint foreign key(`brand_id`) references `brand`(`id`)
);


create table `cart`(
	`id` bigint auto_increment not null,
    `quantity` int ,
    `created_date` datetime,
    `product_id` bigint,
    `user` varchar(55),
    primary key(`id`),
    constraint foreign key (`product_id`) references `product`(`id`)
);

create table `review`(
	`id` bigint auto_increment not null,
    `date` datetime(6) default null,
    `rating` decimal(3,2) default null,
    `description` text default null,
    `user` varchar(55) not null,
    `product_id` bigint,
    primary key (`id`),
    constraint foreign key (`product_id`) references `product`(`id`)
);

create table `coupon`(
	`id` bigint auto_increment not null,
    `code` varchar(155) not null,
    `expire_date` datetime ,
    `percentage` double not null,
    primary key(`id`)
);


create table `messages`(
	`id` bigint auto_increment not null,
    `user` varchar(55) not null,
    `title` varchar(55) default null,
    `question` varchar(255) not null,
    `admin` varchar(55) not null,
    `response` varchar(255) not null,
    `closed` tinyint(1) default 0,
    primary key(`id`)
);

create table `wish_list` (
	`id` bigint auto_increment not null,
    `created_date` datetime,
    `user` varchar(55),
    `product_id` bigint,
    primary key(`id`),
    constraint foreign key (`product_id`) references `product`(`id`)
);


-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------

USE `full-stack-ecommerce`;

--
-- Prep work
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `address`;
SET FOREIGN_KEY_CHECKS=1;

--
-- Table structure for table `address`
--
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `customer`
--
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `orders`
--
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_tracking_number` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `billing_address_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `shipping_address_id` bigint DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_billing_address_id` (`billing_address_id`),
  UNIQUE KEY `UK_shipping_address_id` (`shipping_address_id`),
  KEY `K_customer_id` (`customer_id`),
  CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_billing_address_id` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `order_items`
--
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




