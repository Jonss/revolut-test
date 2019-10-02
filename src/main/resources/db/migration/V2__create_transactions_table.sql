CREATE TABLE transactions (
    id INT(11) UNSIGNED AUTO_INCREMENT,
    amount INT NOT NULL,
    operation enum('SAVING', 'TRANSFER', 'WITHDRAWAL') DEFAULT 'SAVING',
    origin_account_id INT(11) UNSIGNED NOT NULL,
    destiny_account_id INT(11) UNSIGNED NOT NULL,
    external_id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP,
    currency enum('BRL', 'GBP')
);







