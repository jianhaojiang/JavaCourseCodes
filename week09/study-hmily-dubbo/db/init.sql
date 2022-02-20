truncate table account_usd;
truncate table account_rmb;
truncate table froze_account_rmb;
truncate table froze_account_usd;

INSERT INTO `account_usd`(`id`, `user_id`, `money`) VALUES (1, 1, 10.00);
INSERT INTO `account_usd`(`id`, `user_id`, `money`) VALUES (2, 2, 10.00);


INSERT INTO `account_rmb`(`id`, `user_id`, `money`) VALUES (1, 1, 10.00);
INSERT INTO `account_rmb`(`id`, `user_id`, `money`) VALUES (2, 2, 10.00);

