CREATE TABLE location(
    id UUID NOT NULL PRIMARY KEY,
    grid_id VARCHAR(10),
    grid_x INTEGER NOT NULL,
    grid_y INTEGER NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION NOT NULL
);
CREATE TABLE weather_forecast(
    id UUID NOT NULL PRIMARY KEY,
    location_id UUID NOT NULL REFERENCES location(id),
    description VARCHAR,
    rain_quantity FLOAT,
    percent_rain_chance FLOAT(1),
    date_time DATE
);

CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    f_name VARCHAR(20),
    l_name VARCHAR(20),
    role VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE address(
    id UUID NOT NULL PRIMARY KEY,
    location_id UUID NOT NULL REFERENCES location(id),
    address_line1 VARCHAR NOT NULL,
    address_line2 VARCHAR,
    city VARCHAR NOT NULL,
    state CHAR(2) NOT NULL,
    postal_code VARCHAR(10) NOT NULL
);

CREATE TABLE project (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    address_id UUID NOT NULL REFERENCES address(id),
    name VARCHAR(100),
    description VARCHAR
);

