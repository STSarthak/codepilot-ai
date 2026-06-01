# 🚀 CodePilot AI - Operations Runbook

## Architecture Overview
The system consists of independent Spring Boot microservices backed by shared infrastructure. 
- **Builds:** Each service has its own independent `build.gradle`.
- **Dependency Management:** `common-lib` is integrated via a Gradle Composite Build (`includeBuild`), so it automatically compiles from source when you build other services locally.

---

## 🛠️ Local Development Setup

### 1. Start Shared Infrastructure
Start the shared data tier (Postgres, MinIO, Kafka) from the root directory:
```powershell
docker compose up -d
```
**Infrastructure Ports:**
- PostgreSQL: `9000` (Contains `account_db`, `workspace_db`, `intelligence_db`)
- MinIO API: `9001`
- MinIO Console: `9002`
- Kafka: `9092`

### 2. Start Microservices
Open separate terminals for each service and start them in the following order to ensure smooth discovery/config loading:

**1. Discovery Server (Eureka)**
```powershell
cd discovery-service
.\gradlew bootRun
```
*Runs on port 8761*

**2. Config Server**
```powershell
cd config-service
.\gradlew bootRun
```
*Runs on port 8888*

**3. API Gateway**
```powershell
cd api-gateway
.\gradlew bootRun
```
*Runs on port 8080 (Entrypoint for all API requests)*

**4. Backend Services**
```powershell
cd account-service
.\gradlew bootRun
```
*Runs on port 9050*

```powershell
cd workspace-service
.\gradlew bootRun
```
*Runs on port 9020*

```powershell
cd intelligence-service
.\gradlew bootRun
```
*Runs on port 9030*

---

## 🌐 API Gateway Routes (Port 8080)

All external traffic should go to the `api-gateway` on `http://localhost:8080`. The gateway routes traffic as follows:
- `/api/auth/**`, `/api/plans`, `/api/payments/**` ➡️ **account-service**
- `/api/projects/**` ➡️ **workspace-service**
- `/api/chat/**`, `/api/usage/**` ➡️ **intelligence-service**

---

## ☸️ Kubernetes & Proxy Setup

To spin up user workspace previews, the system uses Kubernetes. 

**1. Build the Proxy Image:**
```powershell
docker build -t shuttle-proxy:latest .\k8s\proxy
```

**2. Apply Manifests:**
```powershell
kubectl apply -f k8s\infra.yml
kubectl apply -f k8s\runner-pods.yml
kubectl apply -f k8s\policy.yml
kubectl apply -f k8s\codepilot-proxy.yml
```

**3. Port Forward for Proxy & Redis:**
*Terminal 1 (For Redis route writing):*
```powershell
kubectl port-forward -n codepilot-apps svc/redis-service 6379:6379
```
*Terminal 2 (For Browser access):*
```powershell
kubectl port-forward -n codepilot-apps svc/codepilot-proxy-svc 8090:80
```

---

## 🐳 Containerizing a Service

To build a Docker image for any microservice:
```powershell
cd account-service
.\gradlew build -x test
docker build -t account-service:latest .
```
