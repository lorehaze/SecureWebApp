SET FOREIGN_KEY_CHECKS=0;  -- turn off foreign key checks
TRUNCATE TABLE users.user;  -- truncate tables
TRUNCATE TABLE users.password;
TRUNCATE TABLE users.salt;
SET FOREIGN_KEY_CHECKS=1;  -- turn on foreign key checks