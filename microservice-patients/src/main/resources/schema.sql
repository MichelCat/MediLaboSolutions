-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
-- Setting up medilabo_patient DB
-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------

drop database if exists medilabo_patient;

create database medilabo_patient;
use medilabo_patient;

-- CREATE TABLE patient (
--     id int NOT NULL AUTO_INCREMENT,             -- Patient ID
--     first_name VARCHAR(125),                    -- Patient first name
--     last_name VARCHAR(125),                     -- Patient last name
--     birth_of_date date,                         -- Patient birth of date
--     gender VARCHAR(1),                          -- Patient gender (M=Male/F=Female)
--     address VARCHAR(125),                       -- Patient address
--     phone_number VARCHAR(12),                   -- Patient phone number
--     create_date TIMESTAMP,                      -- Create date
--     update_date TIMESTAMP,                      -- Update date
--
--     constraint pk_patient PRIMARY KEY (id),
--     constraint uc_patient_firstname_lastname_birthofdate UNIQUE KEY (first_name, last_name, birth_of_date)
-- );
