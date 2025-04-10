-- # UC1 - Ability to create a Address Book Service DB

create database address_book;

show databases;

use address_book;




-- # UC2 - create a Table with first and last names, address, city, state, zip, phone number and email as its attributes

CREATE TABLE address_book (
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(20),
    zip VARCHAR(6),
    phone VARCHAR(10),
    email VARCHAR(50)
)