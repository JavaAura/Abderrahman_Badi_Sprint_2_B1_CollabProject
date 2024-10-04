CREATE DATABASE IF NOT EXISTS planify;

USE planify;

-- Table for squads
CREATE TABLE IF NOT EXISTS squad (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table for members
CREATE TABLE IF NOT EXISTS member (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role ENUM('PROJECTMANAGER', 'DEVELOPER', 'DESIGNER') NOT NULL,
    squad_id bigint(20),
    CONSTRAINT fk_member_squad FOREIGN KEY (squad_id) REFERENCES squad(id)
);

-- Table for projects
CREATE TABLE IF NOT EXISTS project (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    project_statut ENUM('PREPARATION', 'IN_PROGRESS', 'PAUSED', 'COMPLETED', 'CANCELED') NOT NULL,
    squad_id bigint(20),
    CONSTRAINT fk_project_squad FOREIGN KEY (squad_id) REFERENCES squad(id)
);

-- Table for tasks
CREATE TABLE IF NOT EXISTS task (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
    task_statut ENUM('TODO', 'DOING', 'DONE') NOT NULL,
    project_id bigint(20),
    CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(id)
);

-- Correctly named pivot table for task-to-member assignments
CREATE TABLE IF NOT EXISTS member_task (
    task_id bigint(20),
    member_id bigint(20),
    assign_date DATETIME NOT NULL,
    CONSTRAINT fk_pivot_task FOREIGN KEY (task_id) REFERENCES task(id),
    CONSTRAINT fk_pivot_member FOREIGN KEY (member_id) REFERENCES member(id)
);
