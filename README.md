# Project name
Movie REST-APi, Springboot, Spring Security and JWT

## Table of Contents
- [Project name](#project-name)
  - [Description](#description)
    - [Installation](#installation)
    - [Usage](#usage)
    - [API Documentation](#api-documentation)
    - [Dependencies](#dependencies)
  - [Tutorials](#tutorials)
  - [License](#license)

## Description
This is a School project, where the assignment was to create a REST-API using Springboot,\
implementing Spring Security and JWT tokens.  I have focused on the implementation of Spring Security and JWT tokens,\
not on the content of the modell, that's why its only 2 variables in the Movie class.
#### this API is a simple movie database, where you as an unregistered user can :
- get a list of all movies in the database
- get a single movie by id
- create a new movie in the database

#### As a registered user you can:
- ontop of the above-mentioned,
- update a movie in the database
- delete a movie in the database

#### As a Admin user you can:
- ontop of the above-mentioned,
- get a list of all users in the database
- get a single user by id
- create a new user in the database
- update a user in the database
- delete a user in the database

This Backend can be applied to a frontend application, where the user can register and login,\
and then use the API to perform the above-mentioned endpoints.

While doing this project I have learned how to implement Spring Security and JWT tokens,\
and how to use Postman to test and document the API.\
I have also gained a deeper understanding for the HTTP protocol and REST-API's.

### Installation
Important!\
if you don't have a local MySQL database running on your computer, the application will not start.\
you will get an error message that the application cant connect to the database.\

1. To use the application you need to have a local MySQL database running on your computer.
    - Download MySQL Community from https://dev.mysql.com/downloads/installer/ and install it.
    - When you have installed MySQL Community you need to create a database called "movies".
    - The application uses password "root" and username "root" to connect to a local MySQL database.
    - please alter the password and username in the application.properties file if yours not the same.

- You could also mount a Docker image of mySql, for help on how to do this please see the Docker homepage,\
  or this tutorial https://youtu.be/kphq2TsVRIs?si=wS20hxnnLG2CAxr4

2. Clone this repository to your computer and open it in your IDE.
   - run the application

### Usage
import the added PostMan collection to PostMan to test the endpoints.
- the "Get One", "Get all" and "Create" endpoints are open for all users.
- the "Update" and "Delete" endpoints are only open for users with the role "USER or "ADMIN".\
  To use them you must first register a user and then login with the user to get a JWT token.\
  Then you must add the token to the authorization header in PostMan.
- To test the "ADMIN" endpoints you must login with a user with the role "ADMIN".\
  you will get a JWT token in return that you must add to the authorization header in PostMan.

### API Documentation
The API documentation  is made using Postman, and is available at \
https://documenter.getpostman.com/view/27137666/2s9YXpVe6E

### Dependencies
All dependencies are included in the pom.xml file.
- Java 17
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- mysql-connector-j
- spring-boot-starter-security
- spring-security-test
- springdoc-openapi-starter-webmvc-ui
- lombok
- spring-boot-starter-oauth2-resource-server
- spring-boot-starter-test

## Tutorials
https://www.youtube.com/watch?v=TeBt0Ike_Tk \
https://www.youtube.com/watch?v=KxqlJblhzfI \
https://www.youtube.com/watch?v=KYNR5js2cXE&t=517s \
https://www.youtube.com/watch?v=UaB-0e76LdQ&t=141s 


## License
MIT License
