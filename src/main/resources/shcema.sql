CREATE TABLE IF NOT EXISTS `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `pw` varchar(255) DEFAULT NULL,
    `role` int(255) DEFAULT NULL,
    `status` int(11) DEFAULT NULL,
    `order_id` int(11) DEFAULT NULL COMMENT '處理訂單中編號',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `user_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `customer_name` varchar(255) DEFAULT NULL,
    `title` varchar(50) DEFAULT NULL,
    `content` varchar(255) DEFAULT NULL,
    `status` int(11) DEFAULT '0',
    `process` varchar(255) DEFAULT NULL,
    `last_update_user` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
