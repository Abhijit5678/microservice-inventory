# Step 1: Base runtime image
FROM eclipse-temurin:17-jre-alpine

# Step 2: Set application directory
WORKDIR /app

# Step 3: Use a wildcard build argument to automatically pick up any compiled jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Step 4: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]