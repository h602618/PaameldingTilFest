DROP TABLE "user" CASCADE;

CREATE TABLE "user"
(
    phone      INTEGER PRIMARY KEY,
    first_name VARCHAR(20)                                             NOT NULL,
    last_name  VARCHAR(20)                                             NOT NULL,
    gender     VARCHAR(6) check (gender = 'male' or gender = 'female') NOT NULL,
    password   VARCHAR(255)                                            NOT NULL --TODO limit in java & javascript/html
);

INSERT INTO "user"
VALUES (23456789, 'Anne', 'Panne', 'female', 'pass'),
       (90123456, 'Arne', 'Arnesen', 'male', 'pass'),
       (90123455, 'Arne', 'Arnesen', 'male', 'pass'),
       (12345678, 'Lars-Petter', 'Helland', 'male', 'pass'),
       (34534534, 'Per', 'Viskelær', 'male', 'pass'),
       (12345676, 'Pål', 'Olsen', 'male', 'pass'),
       (12321378, 'Xx-x', 'Xxx', 'female', 'pass');
SELECT *
FROM "user";