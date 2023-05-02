CREATE TABLE country (
    id IDENTITY not null,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE product (
   id IDENTITY not null,
   name VARCHAR(255),
   country_id BIGINT not null,
   PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT country_id_fk
    FOREIGN KEY (country_id)
    REFERENCES country (id);