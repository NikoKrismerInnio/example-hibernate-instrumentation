CREATE TABLE IF NOT EXISTS asset (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS asset_detail (
    id SERIAL NOT NULL PRIMARY KEY,
    location TEXT,
    customer TEXT,
    region TEXT
);

CREATE TABLE IF NOT EXISTS data_item (
    id SERIAL NOT NULL PRIMARY KEY,
    asset_id INTEGER NOT NULL,
    name TEXT,
    value TEXT
);

CREATE TABLE IF NOT EXISTS property (
    id SERIAL NOT NULL PRIMARY KEY,
    asset_id INTEGER NOT NULL,
    name TEXT,
    value TEXT
);