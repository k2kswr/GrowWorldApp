FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src src
RUN mvn package -DskipTests -q
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","if [ -n \"$DATABASE_URL\" ]; then db=\"${DATABASE_URL#postgresql://}\"; credentials=\"${db%%@*}\"; hostDatabase=\"${db#*@}\"; export SPRING_DATASOURCE_URL=\"jdbc:postgresql://${hostDatabase}\" SPRING_DATASOURCE_USERNAME=\"${credentials%%:*}\" SPRING_DATASOURCE_PASSWORD=\"${credentials#*:}\"; fi; exec java -jar app.jar"]
