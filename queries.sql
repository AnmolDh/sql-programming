-- # UC 1 - Ability to create a payroll service database

create database payroll_service;

show databases;

use payroll_service;




-- # UC 2 - Ability to create a employee payroll table in the payroll service database

CREATE TABLE employee_payroll (
    id INT unsigned NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    salary DOUBLE,
    start_date DATE,
    PRIMARY KEY (id)
);




-- # Ability to create employee payroll data in the payroll service database

INSERT INTO employee_payroll VALUES
    (1, 'Anmol', 0.0, '2000-01-01'),
    (2, 'Ankit', 1.0, '2001-10-01'),
    (3, 'Rishav', 1.0, '2004-09-10');




-- # Ability to retrieve all the employee payroll data

SELECT * FROM employee_payroll;
-- +--+------+------+----------+
-- |id|name  |salary|start_date|
-- +--+------+------+----------+
-- |1 |Anmol |0     |2000-01-01|
-- |2 |Ankit |1     |2001-10-01|
-- |3 |Rishav|1     |2004-09-10|
-- +--+------+------+----------+
