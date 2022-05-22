CREATE TABLE t_order(
	`order_id` VARCHAR(50) PRIMARY KEY,
	`create_time` DATETIME,
	`price` DECIMAL(11,2),
	`status` INT,
	`user_id` INT,
	FOREIGN KEY(`user_id`) REFERENCES t_user(`id`)
);
CREATE TABLE t_order_item(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`count` INT,
	`price` DECIMAL(11,2),
	`total_price` DECIMAL(11,2),
	`order_id` VARCHAR(50),
	FOREIGN KEY(`order_id`) REFERENCES t_order(`order_id`)
);
CREATE TABLE t_user(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(20) NOT NULL UNIQUE,
	`password` VARCHAR(32) NOT NULL,
	`email` VARCHAR(200)
);
CREATE TABLE t_manager(
	`manager_id` INT PRIMARY KEY AUTO_INCREMENT,
	`manager_name` VARCHAR(50) NOT NULL UNIQUE,
	`manager_password` VARCHAR(50) NOT NULL,
	`manager_email` VARCHAR(200)
);
INSERT INTO t_manager(`manager_name`,`manager_password`,`manager_email`) VALUES('admin','admin','admin@126.com');