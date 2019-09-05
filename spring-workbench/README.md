### User Management Application for Crud Ops
##### Initializer project for Spring Boot workbenches, acts kind of an introduction to Spring Core, Boot, MVC and `SOA`, in addition `DAO` abstraction has been practiced.
### Build and run:

* On a Docker Container:
```
mvn clean package
sudo docker build -t spring-workbench .
sudo docker run --network="host" -d --rm --name workbench-1 spring-workbench
```

* On host machine:
```
mvn clean install
java -jar target/springWorkbench-1.0-SNAPSHOT.jar
```