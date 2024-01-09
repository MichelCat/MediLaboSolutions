-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
-- Default values for testing functionality
-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------

insert into patient (first_name, last_name, birth_of_date, gender, address, phone_number) values
('TestNone', 'Test', str_to_date('31/12/1966', '%d/%m/%Y'), 'F', '1 Brookside St', '100-222-3333')
, ('TestBorderline', 'Test', str_to_date('24/06/1945', '%d/%m/%Y'), 'M', '2 High St', '200-333-4444')
, ('TestInDanger', 'Test', str_to_date('18/06/2004', '%d/%m/%Y'), 'M', '3 Club Road', '300-444-5555')
, ('TestEarlyOnset', 'Test', str_to_date('28/06/2002', '%d/%m/%Y'), 'F', '4 Valley Dr', '400-555-6666')
