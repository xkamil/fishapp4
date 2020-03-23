FROM openjdk:11

VOLUME /app
WORKDIR /app
EXPOSE 8080

ENTRYPOINT ["./mvnw","spring-boot:run"]