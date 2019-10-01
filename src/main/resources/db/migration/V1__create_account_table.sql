CREATE TABLE accounts (
    id INT(11) UNSIGNED AUTO_INCREMENT,
    email VARCHAR(120) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    nick_name VARCHAR(120) NOT NULL,
    phone_number VARCHAR(40) NOT NULL UNIQUE,
    external_id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP
);