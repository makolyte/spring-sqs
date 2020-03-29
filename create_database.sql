CREATE DATABASE spring_sqs;
CREATE USER 'spring' @'localhost' IDENTIFIED BY 'password';
GRANT ALL ON spring_sqs.* TO 'spring'@'localhost';