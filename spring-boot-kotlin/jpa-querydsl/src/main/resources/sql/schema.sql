/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

DROP TABLE IF EXISTS `aroma`;
DROP TABLE IF EXISTS `grape`;
DROP TABLE IF EXISTS `importer`;
DROP TABLE IF EXISTS `pairing`;
DROP TABLE IF EXISTS `region`;
DROP TABLE IF EXISTS `share`;
DROP TABLE IF EXISTS `winery`;
DROP TABLE IF EXISTS `wine`;
DROP TABLE IF EXISTS `wine_aromas`;
DROP TABLE IF EXISTS `wine_grapes`;
DROP TABLE IF EXISTS `wine_pairings`;


CREATE TABLE `region`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `parent_id`    bigint                                  DEFAULT NULL,
    `name_english` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `name_korean`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK5cgfpq4u2digwkllynq14k7te` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `winery`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `region_id`    bigint                                  DEFAULT NULL,
    `name_english` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `name_korean`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKaaikoq1wblillkm5pk8x2dnv7` (`region_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `grape`
(
    `acidity`      int    NOT NULL,
    `body`         int    NOT NULL,
    `sweetness`    int    NOT NULL,
    `tannin`       int    NOT NULL,
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `name_english` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `name_korean`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;



CREATE TABLE `share`
(
    `share`     double NOT NULL,
    `grape_id`  bigint DEFAULT NULL,
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `region_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKmu9a4w97i9hvcc7r7x1kbtp27` (`grape_id`),
    KEY `FKca1bv831l6ksoymnxm7vgpi5b` (`region_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `wine`
(
    `acidity`             int    NOT NULL,
    `alcohol`             double NOT NULL,
    `body`                int    NOT NULL,
    `price`               int    NOT NULL,
    `score`               double NOT NULL,
    `serving_temperature` double                                                                         DEFAULT NULL,
    `sweetness`           int    NOT NULL,
    `tannin`              int    NOT NULL,
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `importer_id`         bigint                                                                         DEFAULT NULL,
    `region_id`           bigint                                                                         DEFAULT NULL,
    `winery_id`           bigint                                                                         DEFAULT NULL,
    `grade`               varchar(255) COLLATE utf8mb4_unicode_ci                                        DEFAULT NULL,
    `name_english`        varchar(255) COLLATE utf8mb4_unicode_ci                                        DEFAULT NULL,
    `name_korean`         varchar(255) COLLATE utf8mb4_unicode_ci                                        DEFAULT NULL,
    `style`               varchar(255) COLLATE utf8mb4_unicode_ci                                        DEFAULT NULL,
    `type`                enum ('FORTIFIED','RED','ROSE','SPARKLING','WHITE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKpjwcchieaos0oirwh8jbwepyu` (`importer_id`),
    KEY `FKg8nm9gi93gvmhoadp74owq5cj` (`region_id`),
    KEY `FK6pkmh68x0mq5u913rsad7yrpn` (`winery_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `aroma`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `importer`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `pairing`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `wine_aromas`
(
    `aromas_id` bigint NOT NULL,
    `wine_id`   bigint NOT NULL,
    KEY `FKol62t0yqdx6plo1jy95013bem` (`aromas_id`),
    KEY `FKrad9u3p903dav5rpe9u97wul0` (`wine_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `wine_grapes`
(
    `grape_id` bigint NOT NULL,
    `wine_id`  bigint NOT NULL,
    KEY `FKaon4m462kpi9xtlm9rlhsok5y` (`wine_id`),
    KEY `FKqwpxq37cdxa6tol50nxoo0uiy` (`grape_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `wine_pairings`
(
    `pairings_id` bigint NOT NULL,
    `wine_id`     bigint NOT NULL,
    KEY `FKleaslemmmqoubd1nwk36q0ddc` (`pairings_id`),
    KEY `FK3qm0e5tcakhsjn7yka33es98x` (`wine_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


ALTER TABLE `region`
    ADD CONSTRAINT `FK5cgfpq4u2digwkllynq14k7te` FOREIGN KEY (`parent_id`) REFERENCES `region` (`id`);
ALTER TABLE `share`
    ADD CONSTRAINT `FKca1bv831l6ksoymnxm7vgpi5b` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);
ALTER TABLE `share`
    ADD CONSTRAINT `FKmu9a4w97i9hvcc7r7x1kbtp27` FOREIGN KEY (`grape_id`) REFERENCES `grape` (`id`);
ALTER TABLE `wine`
    ADD CONSTRAINT `FK6pkmh68x0mq5u913rsad7yrpn` FOREIGN KEY (`winery_id`) REFERENCES `winery` (`id`);
ALTER TABLE `wine`
    ADD CONSTRAINT `FKg8nm9gi93gvmhoadp74owq5cj` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);
ALTER TABLE `wine`
    ADD CONSTRAINT `FKpjwcchieaos0oirwh8jbwepyu` FOREIGN KEY (`importer_id`) REFERENCES `importer` (`id`);
ALTER TABLE `wine_aromas`
    ADD CONSTRAINT `FKol62t0yqdx6plo1jy95013bem` FOREIGN KEY (`aromas_id`) REFERENCES `aroma` (`id`);
ALTER TABLE `wine_aromas`
    ADD CONSTRAINT `FKrad9u3p903dav5rpe9u97wul0` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`);
ALTER TABLE `wine_grapes`
    ADD CONSTRAINT `FKaon4m462kpi9xtlm9rlhsok5y` FOREIGN KEY (`wine_id`) REFERENCES `grape` (`id`);
ALTER TABLE `wine_grapes`
    ADD CONSTRAINT `FKqwpxq37cdxa6tol50nxoo0uiy` FOREIGN KEY (`grape_id`) REFERENCES `wine` (`id`);
ALTER TABLE `wine_pairings`
    ADD CONSTRAINT `FK3qm0e5tcakhsjn7yka33es98x` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`);
ALTER TABLE `wine_pairings`
    ADD CONSTRAINT `FKleaslemmmqoubd1nwk36q0ddc` FOREIGN KEY (`pairings_id`) REFERENCES `pairing` (`id`);
ALTER TABLE `winery`
    ADD CONSTRAINT `FKaaikoq1wblillkm5pk8x2dnv7` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);



/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;