CREATE DATABASE IF NOT EXISTS questiondb;
USE questiondb;

CREATE TABLE IF NOT EXISTS question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_title VARCHAR(255) NOT NULL,
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    right_answer VARCHAR(255) NOT NULL,
    difficulty_level VARCHAR(50) NOT NULL,
    category VARCHAR(100) NOT NULL
);
