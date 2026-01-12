CREATE DATABASE IF NOT EXISTS quizdb;
USE quizdb;

-- main quiz table
CREATE TABLE IF NOT EXISTS quiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- element collection table for storing question IDs
CREATE TABLE IF NOT EXISTS quiz_question_ids (
    quiz_id INT NOT NULL,
    question_ids INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);
