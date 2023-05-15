CREATE DATABASE homework_27;

USE homework_27;

CREATE TABLE students(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
email VARCHAR(255)
);

INSERT INTO students(name,email) VALUES
('John','john.gmail.com'),
('Michael','michael.gmail.com'),
('Jimmy','jimmy.gmail.com'),
('Martin','martin.gmail.com'),
('Joshua','joshua.gmail.com'),
('Kate','kate.gmail.com'),
('Elizabeth','elizabeth.gmail.com'),
('Henry','henry.gmail.com'),
('Anna','anna.gmail.com'),
('Macarena','macarena.gmail.com');

