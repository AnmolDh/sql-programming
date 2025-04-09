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