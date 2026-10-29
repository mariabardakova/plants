CREATE TABLE plants (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    species VARCHAR(255) NOT NULL,
    last_watered DATE,
    watering_interval_days INT NOT NULL
);