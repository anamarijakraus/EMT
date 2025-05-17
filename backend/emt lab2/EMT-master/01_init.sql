CREATE TABLE country (
                         id BIGSERIAL  PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         continent VARCHAR(100) NOT NULL
);


CREATE TABLE host (
                      id BIGSERIAL  PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      surname VARCHAR(100) NOT NULL,
                      country_id BIGINT NOT NULL,
                      CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id) on delete cascade on update cascade
);


CREATE TYPE bookingCategory_enum AS ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');

CREATE TABLE booking (
                         id BIGSERIAL  PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         category  bookingCategory_enum NOT NULL,
                         num_rooms INTEGER NOT NULL,
                         host_id BIGINT NOT NULL,
                         CONSTRAINT fk_host FOREIGN KEY (host_id) REFERENCES host(id) on delete cascade on update cascade
);

CREATE TYPE role_enum AS ENUM ('ROLE_USER', 'ROLE_HOST');

CREATE TABLE booking_users
(
    username                   VARCHAR(255) PRIMARY KEY,
    password                   VARCHAR(255) NOT NULL,
    name                       VARCHAR(255) NOT NULL,
    surname                    VARCHAR(255) NOT NULL,
    is_account_non_expired     BOOLEAN DEFAULT TRUE,
    is_account_non_locked      BOOLEAN DEFAULT TRUE,
    is_credentials_non_expired BOOLEAN DEFAULT TRUE,
    is_enabled                 BOOLEAN DEFAULT TRUE,
    role                       role_enum    NOT NULL
);
