CREATE TABLE IF NOT EXISTS fav_product (
    id SERIAL PRIMARY KEY,
    product_id BIGINT,
    account_id BIGINT,
    CONSTRAINT fk_product
    FOREIGN KEY (product_id)
    REFERENCES products(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_account
    FOREIGN KEY (account_id)
    REFERENCES accounts(id)
    ON DELETE CASCADE
    );
