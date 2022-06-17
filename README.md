# budget-backend

## Description

[Budget](https://github.com/fsv2860/budget) is a web application that allows users to create, manipulate, and compare budgets.

This repository contains the application's backend files necessary to manage the database and deploy the backend.

## Deployment

Follow these steps to deploy the backend.

### General requirements

1. Install [Java Development Kit](https://www.oracle.com/java) 16.0.1 or superior.

### Initial database setup

**If you're using a service that provides you a PostgreSQL database you can skip the first two steps.**

2. Install [PostgreSQL](https://www.postgresql.org/).
3. Create the database user **'budget-db-user'** and then the database **'budget-db'**.

### Database configuration

4. Change the (backend) configuration file (**application.properties**) to match your database configuration (url,port, user, password).
5. Start the server.

## Built with

[Spring Boot](https://spring.io/projects/spring-boot)

[PostgreSQL](https://www.postgresql.org/)

[Lombok](https://projectlombok.org/)

[Mapstruct](https://mapstruct.org/)

## See also

[budget - Frontend code](https://github.com/fsv2860/budget-frontend)
