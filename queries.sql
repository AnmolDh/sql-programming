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




-- # UC3 - insert new Contacts to Address Book

INSERT INTO address_book VALUES
     ('Anmol',
      'Dhiman',
      '32, West Avenue',
      'SYD',
      'NSW',
      '100211',
      '8319832222',
      'test@test.com'),
     ('Rishav',
      'Thakur',
      '42, East Avenue',
      'SYD',
      'NSW',
      '100210',
      '3922199222',
      'test@testmail.com');
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |first_name|last_name|address        |city|state|zip   |phone     |email            |
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |Anmol     |Dhiman   |32, West Avenue|SYD |NSW  |100211|8319832222|test@test.com    |
-- |Rishav    |Thakur   |42, East Avenue|SYD |NSW  |100210|3922199222|test@testmail.com|
-- +----------+---------+---------------+----+-----+------+----------+-----------------+




-- # UC4 - edit existing contact person using their name

UPDATE address_book SET zip='100211' WHERE first_name='Rishav';
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |first_name|last_name|address        |city|state|zip   |phone     |email            |
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |Anmol     |Dhiman   |32, West Avenue|SYD |NSW  |100211|8319832222|test@test.com    |
-- |Rishav    |Thakur   |42, East Avenue|SYD |NSW  |100211|3922199222|test@testmail.com|
-- +----------+---------+---------------+----+-----+------+----------+-----------------+




-- # UC5 - delete a person using person's name

DELETE FROM address_book WHERE first_name='Rishav';
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |first_name|last_name|address        |city|state|zip   |phone     |email            |
-- +----------+---------+---------------+----+-----+------+----------+-----------------+
-- |Anmol     |Dhiman   |32, West Avenue|SYD |NSW  |100211|8319832222|test@test.com    |
-- +----------+---------+---------------+----+-----+------+----------+-----------------+