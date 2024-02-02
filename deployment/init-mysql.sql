-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
-- Setting up medilabo_patient DB
-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
drop database if exists medilabo_patient;

create database medilabo_patient;
use medilabo_patient;

CREATE TABLE patient (
    id int NOT NULL AUTO_INCREMENT,             -- Patient ID
    first_name VARCHAR(125),                    -- Patient first name
    last_name VARCHAR(125),                     -- Patient last name
    birth_of_date date,                         -- Patient birth of date
    gender enum('F','M'),                       -- Patient gender (M=Male/F=Female)
    address VARCHAR(125),                       -- Patient address
    phone_number VARCHAR(12),                   -- Patient phone number
    create_date TIMESTAMP,                      -- Create date
    update_date TIMESTAMP,                      -- Update date

    constraint pk_patient PRIMARY KEY (id),
    constraint uc_patient_firstname_lastname_birthofdate UNIQUE KEY (first_name, last_name, birth_of_date)
);


insert into patient (id, first_name, last_name, birth_of_date, gender, address, phone_number) values
(1,'TestNone', 'Test', str_to_date('31/12/1966', '%d/%m/%Y'), 'F', '1 Brookside St', '100-222-3333')
, (2, 'TestBorderline', 'Test', str_to_date('24/06/1945', '%d/%m/%Y'), 'M', '2 High St', '200-333-4444')
, (3, 'TestInDanger', 'Test', str_to_date('18/06/2004', '%d/%m/%Y'), 'M', '3 Club Road', '300-444-5555')
, (4, 'TestEarlyOnset', 'Test', str_to_date('28/06/2002', '%d/%m/%Y'), 'F', '4 Valley Dr', '400-555-6666');
