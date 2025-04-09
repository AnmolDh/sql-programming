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




-- # UC 3 - Ability to create employee payroll data in the payroll service database

INSERT INTO employee_payroll VALUES
    (1, 'Anmol', 0.0, '2000-01-01'),
    (2, 'Ankit', 1.0, '2001-10-01'),
    (3, 'Rishav', 1.0, '2004-09-10');




-- # UC 4 - Ability to retrieve all the employee payroll data

SELECT * FROM employee_payroll;
-- +--+------+------+----------+
-- |id|name  |salary|start_date|
-- +--+------+------+----------+
-- |1 |Anmol |0     |2000-01-01|
-- |2 |Ankit |1     |2001-10-01|
-- |3 |Rishav|1     |2004-09-10|
-- +--+------+------+----------+




-- # UC 5 - Ability to retrieve salary data for a particular employee as well as all employees who have joined in a particular data range

SELECT salary FROM employee_payroll WHERE name='Anmol';
-- +------+
-- |salary|
-- +------+
-- |0     |
-- +------+

SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST('2001-01-01' AS DATE) AND DATE(NOW());
-- +--+------+------+----------+
-- |id|name  |salary|start_date|
-- +--+------+------+----------+
-- |2 |Ankit |1     |2001-10-01|
-- |3 |Rishav|1     |2004-09-10|
-- +--+------+------+----------+




-- # UC 6 - Ability to add Gender to Employee Payroll Table and Update the Rows to reflect the correct Employee Gender

ALTER TABLE employee_payroll ADD COLUMN gender VARCHAR(1);

UPDATE employee_payroll SET gender='M' WHERE id BETWEEN 1 AND 3;
-- +--+------+------+----------+------+
-- |id|name  |salary|start_date|gender|
-- +--+------+------+----------+------+
-- |1 |Anmol |0     |2000-01-01|M     |
-- |2 |Ankit |1     |2001-10-01|M     |
-- |3 |Rishav|1     |2004-09-10|M     |
-- +--+------+------+----------+------+

