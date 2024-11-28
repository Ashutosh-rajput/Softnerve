# Stage 1: Build
FROM maven:3.9.9-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .

# Download dependencies based on the pom.xml (caching will be used if no changes)
RUN mvn dependency:go-offline

# Copy the source files after dependencies are set up
COPY src ./src

# Build the application, skipping tests (this step will be cached if src/ doesn't change)
RUN mvn clean package -DskipTests
#RUN mvn clean package

# Stage 2: Runtime
FROM gcr.io/distroless/java21-debian12 AS final

# Copy the built JAR from the build stage to the runtime stage
COPY --from=build /app/target/Softnerve.jar /app/Softnerve.jar

# Expose the application port
EXPOSE 8080

# Define the entry point to run the application with the prod profile
ENTRYPOINT ["java", "-jar", "/app.jar"]