## Post and Comment Management Application for Crud Ops
##### A system which has posts and comments with relation as an infrastructure has been implemented. 
Following terms has been practiced:
* Spring Core, Boot, MVC
* Spring MVC request chain (SOA, `Controller`, `Service`, `Repository`) and `ResponseEntity`
* Dependency Injection, IOC Container, REST Service
* Spring Data, JPA, Hibernate, `JpaRepository`, `PagingAndSortingRepository`, `JpaAuditing`
* Java's vital data structures, enum and `RuntimeException`

### Build and run:

* On a Docker Container:
```
mvn clean package
sudo docker build -t spring-data-and-more .
sudo docker run --network="host" --rm -d --name workbench-2 spring-data-and-more
```

* On host machine:
```
mvn clean install
java -jar -Dspring.profiles.active=dev target/spring-data-and-more-1.0-SNAPSHOT.jar
```