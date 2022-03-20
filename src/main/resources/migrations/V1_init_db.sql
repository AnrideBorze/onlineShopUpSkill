CREATE TABLE products(
   id SERIAL PRIMARY KEY,
   name VARCHAR(50),
   price VARCHAR(50),
   creation_date DATE
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(50),
    password VARCHAR(50),
    salt VARCHAR(50)
);