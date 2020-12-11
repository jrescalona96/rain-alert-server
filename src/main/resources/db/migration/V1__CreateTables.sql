CREATE TABLE weather_forecast(
    id UUID NOT NULL PRIMARY KEY,
    description VARCHAR,
    rainQuantity FLOAT,
    rainPercentage FLOAT(1)
);

CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    f_name VARCHAR(20),
    l_name VARCHAR(20),
    role VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE location(
    id UUID NOT NULL PRIMARY KEY,
    weather_forecast_id UUID NOT NULL REFERENCES weather_forecast(id),
    gridId VARCHAR(10),
    gridX INTEGER NOT NULL,
    gridY INTEGER NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE address(
    id UUID NOT NULL PRIMARY KEY,
    location_id UUID NOT NULL REFERENCES location(id),
    addressLine1 VARCHAR NOT NULL,
    addressLine2 VARCHAR,
    city VARCHAR NOT NULL,
    state CHAR(2) NOT NULL,
    postalCode VARCHAR(10) NOT NULL
);

CREATE TABLE project (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    address_id UUID NOT NULL REFERENCES address(id),
    name VARCHAR(100),
    description VARCHAR
);

