DELETE FROM users;

INSERT INTO Users(id, user_type, user_name, password, tech_code,
                  first_name, last_name, phone, email,
                  birthdate, street, street_number, city, zip_code, state, country)
VALUES (4001, 'TECHNICIAN', 'tech1', 'passTech123', 'TECH001',
        'Christos', 'Brown', '3216549870', 'david.brown@example.com',
        '1985-09-15', 'Elm Street', '10', 'San Francisco', '94102', 'LA', 'USA');