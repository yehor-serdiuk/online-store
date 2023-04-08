CREATE TABLE country (
    id BIGINT not null,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE sneakers (
   id BIGINT not null,
   name VARCHAR(255),
   country_id BIGINT not null,
   PRIMARY KEY (id)
);

ALTER TABLE sneakers
    ADD CONSTRAINT country_id_fk
    FOREIGN KEY (country_id)
    REFERENCES country (id);
