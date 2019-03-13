/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.24 : Database - clojure_database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clojure_database` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `clojure_database`;

/*Table structure for table `chef` */

DROP TABLE IF EXISTS `chef`;

CREATE TABLE `chef` (
  `chef_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`chef_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

/*Data for the table `chef` */

insert  into `chef`(`chef_id`,`name`,`surname`) values (1,'Filip ','Karic'),(2,'Milan','Markovic'),(3,'Milica','Jevtic'),(4,'Petar','Gajic');

/*Table structure for table `daily_menu` */

DROP TABLE IF EXISTS `daily_menu`;

CREATE TABLE `daily_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  `date_created` date DEFAULT NULL,
  `chef` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `fk_dailymenu_chef` (`chef`),
  KEY `fk_dailymenu_type` (`type`),
  CONSTRAINT `fk_dailymenu_chef` FOREIGN KEY (`chef`) REFERENCES `chef` (`chef_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dailymenu_type` FOREIGN KEY (`type`) REFERENCES `daily_menu_type` (`menu_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

/*Data for the table `daily_menu` */

insert  into `daily_menu`(`menu_id`,`name`,`type`,`date_created`,`chef`) values (1,'Christmas Menu',1,'2019-02-25',1),(4,'Easter day menu',2,'2012-12-12',2),(5,'March Menu',4,'2019-03-09',1),(6,'Menu 13.03.19',3,'2019-03-12',1),(7,'Spring Menu',5,'2019-03-01',1),(8,'Markos Choice',6,NULL,2);

/*Table structure for table `daily_menu_type` */

DROP TABLE IF EXISTS `daily_menu_type`;

CREATE TABLE `daily_menu_type` (
  `menu_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_type_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`menu_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

/*Data for the table `daily_menu_type` */

insert  into `daily_menu_type`(`menu_type_id`,`menu_type_name`) values (1,'Special'),(2,'Holiday'),(3,'Daily'),(4,'Monthly'),(5,'Season'),(6,'Promotion');

/*Table structure for table `meal` */

DROP TABLE IF EXISTS `meal`;

CREATE TABLE `meal` (
  `meal_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meal_name` varchar(60) NOT NULL,
  `kcal_100g` double DEFAULT '0',
  `proteins_100g` double DEFAULT '0',
  `carbs_100g` double DEFAULT '0',
  `fats_100g` double DEFAULT '0',
  `meal_type` varchar(32) NOT NULL DEFAULT '0',
  PRIMARY KEY (`meal_id`),
  KEY `fk_topjelaJelo` (`meal_type`),
  CONSTRAINT `fk_topjelaJelo` FOREIGN KEY (`meal_type`) REFERENCES `meal_type` (`meal_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `meal` */

insert  into `meal`(`meal_id`,`meal_name`,`kcal_100g`,`proteins_100g`,`carbs_100g`,`fats_100g`,`meal_type`) values (2,'Banana',1.09,22,22.83,0.33,'2'),(3,'Cesar salad',170,5.03,6.52,14.17,'3'),(4,'Hamburger',360,14,4,32,'6'),(5,'Popcorn',55,0.99,6.29,3.09,'8'),(6,'Mashed potatoes',113,1.95,16.9,4.19,'5'),(7,'Cabbage salad',20,1,5,0,'5'),(8,'Sarma',126,13,10.8,4.2,'1'),(10,'Oatmeal',70,2.28,12.69,1.36,'1'),(11,'Chicken soup',75,4.05,9.35,2.46,'4'),(12,'Chicken breast',214,26.8,0,11.06,'1'),(14,'Beer',43,0.46,3.55,0,'1'),(16,'Ham and cheese sandwich',241,14.17,22.84,10.6,'8'),(17,'Cooked rice',108,3.23,18.39,2.15,'1'),(18,'Tuna salad',144,23.33,0,4.9,'8'),(19,'Fruit salad',156,1.43,22.94,7.61,'8'),(20,'Fruit joghurt',150,6,29,1.5,'7');

/*Table structure for table `meal_type` */

DROP TABLE IF EXISTS `meal_type`;

CREATE TABLE `meal_type` (
  `meal_type_id` varchar(32) NOT NULL,
  `meal_type_name` varchar(30) NOT NULL DEFAULT 'Ostalo',
  PRIMARY KEY (`meal_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `meal_type` */

insert  into `meal_type`(`meal_type_id`,`meal_type_name`) values ('1','Appetizer'),('2','Main course'),('3','Chowder'),('4','Soup'),('5','Salad'),('6','Sandwich'),('7','Dessert'),('8','Other');

/*Table structure for table `menu_meal` */

DROP TABLE IF EXISTS `menu_meal`;

CREATE TABLE `menu_meal` (
  `menu_meal_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`menu_meal_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `menu_meal` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
