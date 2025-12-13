FROM eclipse-temurin:21-alpine

WORKDIR /app



COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -B

CMD ["./mvnw", "spring-boot:run"]