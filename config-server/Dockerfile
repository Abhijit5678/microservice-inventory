# Step 1: Use an official Eclipse Temurin JRE base image (Java 17 assumed, adjust if using 21)
FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl

# Step 2: Set the working directory inside the container
WORKDIR /app



# Step 3: Copy the compiled jar file from your target folder into the container
# Note: Make sure the jar name matches your actual built artifact name
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port the Config Server runs on
EXPOSE 8888

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]