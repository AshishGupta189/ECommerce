# REST API for an E-Commerce Application


* I had developed this REST API for an e-commerce application. This API performs all the fundamental CRUD operations of any e-commerce platform with user validation at every step.


## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Modules

* Login, Logout Module
* Admin Module
* Customer Module
* Product Module
* Cart Module
* Order Module

## Features

* Admin and Customer authentication & validation with session token having validity of 1 hour for security purposes
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admin with valid session token can add/delete products and category from main database
    * Admin can access the details of different customers, orders
* Customer Features:
    * Registering themselves with application, and logging in to get the valid session token
    * Viewing different products and adding them to cart and placing orders
    * Only logged in user can access his orders.
    * Users who are not logged in can add/remove products to their cart.


## Installation & Run

* Before running the API server, you should update the database config inside the application.properties file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8009

    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root


