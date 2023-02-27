# budget-backend

## Description

[Project description](https://github.com/fsv2860/budget)

[Frontend repository](https://github.com/fsv2860/budget-frontend)

This repository contains the instructions to install the database and deploy the application's backend.

## Deployment

Follow these steps to deploy the backend.

### General requirements

1. Install [Java Development Kit](https://www.oracle.com/java) 16.0.1 or superior.

### Database configuration (MongoDB Atlas)

2. Create a database user and the database.
3. Create a connection string.

   - Go your `Database Deployments`.
   - Click `Create`.
   - Click `Connect your application`.
   - Select `Driver` (Java)
   - Se;ect `Version` (4.3 or later).

4. Copy the connection string and database name and paste them into the (backend) configuration file (**application.properties**) in the fields **spring.data.mongodb.uri** and **spring.data.mongodb-database**.
5. Start the server.

## Built with

[Spring Boot](https://spring.io/projects/spring-boot)

[MongoDB Atlas](https://www.mongodb.com/)

[Lombok](https://projectlombok.org/)

[Mapstruct](https://mapstruct.org/)
