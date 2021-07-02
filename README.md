# Flight Inventory Solution
A simple Java Spring boot application for Flight Inventory Solution with Unit test cases.

## Technology Stack Used:
* Spring Boot framework
* Junit - for unit testing
* Mockito - for mocking object
* Lombok - for reducing boilerplate code form model/data
* MySQL - for data persistence

## How to compile and start
Java 11 and Maven will be required for compiling and running the project.
Update the application.properties file as per your need.
The file contains the following properties

File: application.properties

```
    spring.application.name=Flight-Inventory-Solution
    server.port=8080
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/flightinventorysolution
    spring.datasource.username=
    spring.datasource.password=
    spring.datasource.driver-class-name= com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql = true
```

### Steps to run the application
* Run mvn clean install to compile application and run tests.
* Goto /target folder and run java -jar flightinventorysolution-0.0.1-SNAPSHOT.jar
* the application will be started on port number 8080
* Open browser and hit http://localhost:8080

## API
The response of API will be in following format
```
[
    {
        "id": 3,
        "carrierCode": "EVY",
        "flightNumber": 100,
        "flightDate": "2021-10-02T00:00:00.000+00:00",
        "origin": "Karachi",
        "destination": "Chemnitz"
    },
    {
        "id": 4,
        "carrierCode": "EVY",
        "flightNumber": 100,
        "flightDate": "2021-10-02T00:00:00.000+00:00",
        "origin": "Karachi",
        "destination": "Chemnitz"
    }
]
```

| Endpoint | Request | Response |
| --- | --- | --- |
| POST /api/auth/login | ```{ "username": "user", "password": "123456789" }``` | ```{ "jwt": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjI1MTQ5MjIwLCJleHAiOjE2MjUyMzU2MjB9.JsuGAX4nxsSHw7V_vECTTkiXvAE2G_6b7w60PEFeSNPRdDb2t1g_fxvu4zlN-98j0anl0QVdqV9qEONtCQpCzA", "userDetails": { "id": 2, "username": "user", "email": "user@gmail.com", "authorities": [ { "authority": "ROLE_USER" } ], "enabled": true, "accountNonLocked": true, "accountNonExpired": true, "credentialsNonExpired": true } }```
| GET /api/flights | Retrieves all flights | ``` [ { "id": 3, "carrierCode": "EVY", "flightNumber": 100, "flightDate": "2021-10-02T00:00:00.000+00:00", "origin": "Karachi", "destination": "Chemnitz" }, { "id": 4, "carrierCode": "EVY", "flightNumber": 100, "flightDate": "2021-10-02T00:00:00.000+00:00", "origin": "Karachi", "destination": "Chemnitz" } ] ```
| GET /api/flights/3 | Retrieve Flight by ID 3 | ``` { "id": 3, "carrierCode": "EVY", "flightNumber": 100, "flightDate": "2021-10-02T00:00:00.000+00:00", "origin": "Karachi", "destination": "Chemnitz" } ```
| DELETE /api/flights/2 | Delete Flight by ID 3 | ``` { "message": "Record deleted successfully!" } ```
| POST /api/flights | ``` { "carrierCode": "EVY", "flightNumber": "100", "flightDate": "2021-10-02", "origin": "Karachi", "destination": "Chemnitz" } ``` | ``` { "id": 4, "carrierCode": "EVY", "flightNumber": 100, "flightDate": "2021-10-02T00:00:00.000+00:00", "origin": "Karachi", "destination": "Chemnitz" } ```
| PUT /api/flights/2 | Update record by ID 2 ``` { "carrierCode": "AAA", "flightNumber": "100", "flightDate": "2021-10-02", "origin": "Karachi", "destination": "Chemnitz" } ``` | ``` { "id": 2, "carrierCode": "AAA", "flightNumber": 100, "flightDate": "2021-10-02T00:00:00.000+00:00", "origin": "Karachi", "destination": "Chemnitz" } ```
