# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

# Add Maintainer Info
LABEL maintainer="yesilyurt.selim@metu.edu.tr"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/springWorkbench-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} springWorkbench-1.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springWorkbench-1.jar"]

# Notice java.security.egd configiuration to Avoid JVM delays caused by random number generation
# Check https://ruleoftech.com/2016/avoiding-jvm-delays-caused-by-random-number-generation

#######
# sudo docker build -t spring-workbench .
# sudo docker run --network="host" -d --rm --name workbench-1 spring-workbench
#######