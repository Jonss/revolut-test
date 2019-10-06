CREATE INDEX idx_accounts ON accounts(email, external_id);
CREATE INDEX idx_transactions ON transactions(external_id);