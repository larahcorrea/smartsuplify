FROM ringcentral/maven:3.8.2-jdk17 as build
COPY . .
RUN mvn clean package -DskipTests
#Package
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build target/smartsuplify-0.0.1-SNAPSHOT.jar /opt/smartsuplify-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/smartsuplify-0.0.1-SNAPSHOT.jar"]