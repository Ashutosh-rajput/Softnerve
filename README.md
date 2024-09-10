# Student Management System - Spring Boot Application

This project is designed to manage student information and offers functions for creating, updating, deleting, and retrieving student records. Additionally, it includes a login API for authentication using JWT tokens. The `create student` API is an open endpoint. To use it, you will need to first log in, then copy the JWT token and paste it into the header for authentication.

## Features
- **CRUD Operations on Student Entity**: Create, Read, Update, Delete student records.
- **JWT Authentication**: Secure your endpoints with token-based authentication.
- **MongoDB Database**: Uses MongoDB as the database.
- **Swagger Documentation**: Easily test and view all available APIs.
- **Docker and Kubernetes**: Containerized and deployable on Kubernetes.

---

## Running the Application on Kubernetes

1. Use any Kubernetes engine (e.g., Minikube).

2. Apply the MongoDB YAML file:
   ```bash
   kubectl apply -f mongo-deployment.yaml
3. Apply the Application YAML file:
   ```bash
   kubectl apply -f spring-deployment.yaml
4. Use this Command to get the url.
   ```bash
   minikube service softnerve-springboot-service --url
5. Now you can use swagger to use the application.

