create table if not exists accounts (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    first_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
)