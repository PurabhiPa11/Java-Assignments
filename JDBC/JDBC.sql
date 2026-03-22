CREATE DATABASE college1;
USE college1;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    branch VARCHAR(50)
);

INSERT INTO students VALUES
(1, 'Purabhi', 'cough'),
(2, 'Richal', 'fever');