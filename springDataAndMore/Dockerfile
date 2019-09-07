# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

# Add Maintainer Info
LABEL maintainer="yesilyurt.selim@metu.edu.tr"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/spring-data-and-more-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-data-and-more.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/spring-data-and-more.jar"]

# Btw i could not see why below ENTRYPOINT does not recognize my active profile, so I had to specify active profile in docker run command:
# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-jar","/spring-data-and-more.jar"]

# Notice java.security.egd configiuration to Avoid JVM delays caused by random number generation
# Check https://ruleoftech.com/2016/avoiding-jvm-delays-caused-by-random-number-generation

#######
# sudo docker build -t spring-data-and-more .
# sudo docker run -e"SPRING_PROFILES_ACTIVE=dev" --network="host" -d --rm --name workbench-2 spring-data-and-more
#######