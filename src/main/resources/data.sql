-- 傾印  資料表 genesis.user 結構


-- 正在傾印表格  genesis.user 的資料：~5 rows (近似值)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `pw`, `role`, `status`, `order_id`) VALUES
	(1, 'a', 'a', 1, 0, NULL),
	(2, 'b', 'b', 1, 0, NULL),
	(3, 'c', 'c', 1, 0, NULL),
	(4, 'd', 'd', 2, 0, NULL),
	(5, 'e', 'e', 3, 0, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
