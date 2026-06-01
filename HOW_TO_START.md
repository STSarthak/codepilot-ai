# Starting the Decoupled Microservices

Your microservices are fully independent, but they are configured to use a **Gradle Composite Build** for local development. This means they will automatically compile the `common-lib` from source whenever they need it. You do NOT need to manually publish it.

## Step 1: Start Shared Infrastructure

The shared database, Kafka, and MinIO remain at the root. Start them first:

```powershell
docker compose up -d
```

## Step 2: Start the Microservices

You can start each microservice directly from its own directory. Gradle will automatically handle building `common-lib` in the background for you.

For example, to start the `account-service`:

```powershell
cd account-service
.\gradlew bootRun
```

Repeat this for all other required services (`discovery-service`, `config-service`, `api-gateway`, etc.).

## Containerization (Optional)

Each service contains a `Dockerfile`. To build a Docker image for a specific service:

1. Build the jar: `.\gradlew build -x test`
2. Build the image: `docker build -t <service-name>:latest .`
