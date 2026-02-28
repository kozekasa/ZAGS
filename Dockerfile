FROM maven:3.9.6-eclipse-temurin-21-jammy

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY . .

CMD ["mvn", "clean", "test"]