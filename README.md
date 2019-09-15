# spring-workbench
### A Personal Workbench for Spring Framework where I am cultivating little workbenches and doing some crazy experiments on them.

There are currently 3 parts of this workbench:
* [spring-workbench](https://github.com/ysyesilyurt/spring-workbench/tree/master/spring-workbench), this is the first one which acts kind of an introduction, below	content is practiced:
	* `Spring Core, Boot, MVC`
	* Layered Architecture and REST.
	* Dependency Injection and IOC Container,
    * `DAO` abstraction (as a way to access data).
	* `Lombok` for less boilerplate code.
	* `Postman` for endpoint tests.
	* `Docker` for containerization issues of backend infrastructures.
* [springDataAndMore](https://github.com/ysyesilyurt/spring-workbench/tree/master/springDataAndMore), second one which is kind of a developed form of first one, below content is practiced on top of first:
	* `Spring Data` with `JPA`, and its implementation, `Hibernate`.
	* `Repository` along with `JpaAuditing` structure instead of plain `DAO`s with the help of `JpaRepository` and `PagingAndSortingRepository`.
	* `ResponseEntity` and error handling mechanisms with resource management. 
	* Java's vital data structures, enum and `Optional`.
* [potential-playlist](https://github.com/ysyesilyurt/potential-playlist), the third one that is built on top of the second and in which I introduce:
	* `Spring Hateoas` for ultra-Rest practices in Spring-based web applications.
	* `Spring Security` for practicing security concerns (Authentication/Authorization and much more) of web applications and their management in a Spring-based backend. 
	* `DTO`s along with `MapStruct` library to create handy `DTO`s, `DbProjections` and `Models` for internal usage in the application.
	* `liquibase` for practicing manipulations of db tables from codebase. 
	* `jUnit` and `Spring Test` for practicing testing mechanisms of Spring Boot applications.

All 3 workbenches has their own:
* `Postman` collection which I personally created to test the apps.
* `Dockerfiles` to run these apps on docker containers. 
