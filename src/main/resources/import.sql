DELETE FROM Users;
delete from Customers;

INSERT INTO Users(id, user_type, user_name, password, tech_code,
                  first_name, last_name, phone, email,
                  birthdate, street, street_number, city, zip_code, state, country)
VALUES (4001, 'TECHNICIAN', 'tech1', 'passTech123', 'TECH001',
        'Christos', 'Brown', '3216549870', 'david.brown@example.com',
        '1985-09-15', 'Elm Street', '10', 'San Francisco',
        '94102', 'LA', 'USA');

INSERT INTO Customers(id, customer_code, first_name, last_name, phone, email,
                  birthdate, street, street_number, city, zip_code, state, country)
VALUES (5001, 'cust1', 'Christos', 'Doe',
        '6911111111', 'c@b.gr', '1999-09-15', 'Elm Street',
        '666', 'Minitown', '10122', 'California',
        'USA');

INSERT INTO Users(id, user_type, user_name, password, emp_code,
                  first_name, last_name, phone, email,
                  birthdate, street, street_number, city, zip_code, state, country)
VALUES (6001, 'CUSTOMERSUPPORT', 'empl1', 'passempl123', 'EMPL001',
        'Christos', 'Emplower', '1233211232', 'test.2@example.com',
        '1995-12-03', 'Alk Street', '101', 'San Francisco',
        '94541', 'LA', 'USA');