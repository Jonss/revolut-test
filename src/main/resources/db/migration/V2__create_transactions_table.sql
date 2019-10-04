CREATE TABLE transactions (
    id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    amount INT NOT NULL,
    operation enum('SAVING', 'TRANSFER', 'WITHDRAWAL', 'CHARGEBACK') DEFAULT 'SAVING',
    origin_account_id INT UNSIGNED NOT NULL,
    destiny_account_id INT UNSIGNED NOT NULL,
    external_id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP,
    currency enum('BRL', 'GBP')
);

INSERT INTO accounts(email, full_name, nick_name, phone_number, external_id, created_at)
VALUES ('issuer@revolut.co.uk', 'Revolut', 'Revolut', '+44 9999999999999', '5f372e4f-392e-461f-9f68-4b5582bfa735', NOW());


CREATE TABLE balances (
    id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    total INT NOT NULL,
    account_id INT UNSIGNED NOT NULL,
    last_deposit TIMESTAMP,
    currency enum('BRL', 'GBP')
);







