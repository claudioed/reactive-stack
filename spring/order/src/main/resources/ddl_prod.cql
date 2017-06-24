CREATE TYPE sales.product ( id text,  name text, description text);

CREATE TABLE sales.orders ( order_number text PRIMARY KEY,  customer text, items list<frozen<product>>);