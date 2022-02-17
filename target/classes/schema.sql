CREATE TABLE IF NOT EXISTS PRODUCTS
(
    `id`           int AUTO_INCREMENT NOT NULL,
    `name`         varchar(100)       NOT NULL,
    `price`        float              NOT NULL,
    `creationDate` date               NOT NULL,
    PRIMARY KEY (`id`)
);