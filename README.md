 # Planify

A simple Java web application for managing projects, tasks, squads and members.

## Table of Contents

- [Project Overview](#project-overview)
- [Installation](#installation)
- [Structure](#structure)
- [Features](#features)
- [Technologies](#technologies)


## Project Overview

**Context**:  
Planify is a collaborative task management solution designed to replace an existing console-based application. The goal is to develop a full-fledged web application that integrates project, task, and team management using a relational database. This project applies advanced Java concepts and web technologies for enhanced functionality and user experience.

**Objectives**:
- Develop a Java web application using Servlets, JSP, and JSTL.
- Implement CRUD operations for projects, tasks, members, and squads.
- Adapt the existing database for web use.
- Build an intuitive user interface.
- Follow agile project management practices using Scrum.

## Installation

### Prerequisites

- Java 8 or higher
- Apache Maven
- MySQL Server
- Apache Tomcat v9.x

### Setup environment variable

1. **For windows:**
   ```cmd
   set DB_URL= **Your MySQL URL**
   set DB_USER= **DB User**
   set DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

2. **For linux based:**
   ```bash
   export DB_URL= **Your MySQL URL**
   export DB_USER= **DB User**
   export DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

### Setup your database:

1. Ensure your MySQL server is running.
2. Navigate to the directory containing `Database.sql`.
3. Run the following command to create the database and tables:
   ```bash
   mysql -u your_username -p your_database < Database.sql
   

### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Yorften/CollabProject.git
   cd CollabProject

2. **Build the application:**
   ```sh
   mvn clean install

3. **Compile the application:**
   ```sh
   mvn package

4. **Deploy:**

- Deploy the WAR file in Apache Tomcat by copying the WAR from the /target folder to Tomcat's webapps directory.
- Start the Tomcat server and access the application in your browser at http://localhost:8080/Planify.

5. **Run with Eclipse/IntelliJ IDEA (optional)**

- Open the project in your ide.
- Build maven dependencies.
- Run the app or server.

## Structure

- **Model Layer**:  
  Defines entities such as `Project`, `Task`, `Member`, and `Squad`. These represent the application's core business domain.
  
- **Controller Layer**:  
  Handles user interactions, receives input from the UI, and communicates with the Service layer to process the requests.
  
- **Repository Layer**:  
  Provides an abstraction for data access. This layer interacts with the database using JDBC and handles CRUD operations.
  
- **Service Layer**:  
  Contains business logic and orchestrates operations between the Controller and Repository layers.
  
- **Presentation Layer**:  
  Uses JSP and JSTL for rendering views and handling front-end user interactions.

## Features

1. **Project Management**:
   - Create, update, delete, and search projects.
   - View project statistics (e.g., number of tasks and members).

2. **Task Management**:
   - List tasks by project with pagination.
   - Add, update, delete, and assign tasks to members.
   - Track task progress by updating the status (To Do, Doing, Done).

3. **Squad Management**:
   - List squad members with pagination.
   - Add, update, and remove squads and members.
   - View tasks assigned to each member.

4. **Advanced Features**:
   - User-friendly and responsive UI built with Bootstrap.
   - Role-based task assignments (Project Manager, Developer, Designer).
   - Statistics tracking for projects and tasks.

## Technologies

- **Java 8**: Core language used for development.
- **Servlets, JSP, JSTL**: For handling requests, business logic, and rendering views.
- **Apache Maven**: For dependency management and project build.
- **MySQL**: Database for storing application data.
- **JDBC**: Database connectivity.
- **Apache Tomcat**: Web server for deploying the application.
- **JUnit**: For unit testing.
- **Bootstrap**: For responsive UI design.
- **JIRA**: For project management using Scrum methodology.
- **Git**: For version control with branches.