# CodePilot AI

## Prerequisites

- Java 21
- Docker Desktop with Kubernetes enabled
- `kubectl`
- MinIO buckets and starter project data

## Environment Variables

Set these before starting the Spring Boot app:

```powershell
$env:JWT_SECRET_KEY="your-jwt-secret"
$env:OPENAPI_KEY="your-openrouter-key"
$env:STRIPE_SECRET_KEY="your-stripe-secret"
$env:STRIPE_WEBHOOK_SECRET_KEY="your-stripe-webhook-secret"
```

Optional variables:

```powershell
$env:MINIO_URL="http://localhost:9001"
$env:MINIO_ACCESS_KEY="minioadmin"
$env:MINIO_SECRET_KEY="minioadmin123"
$env:MINIO_PROJECT_BUCKET="projects"
$env:REDIS_HOST="localhost"
$env:REDIS_PORT="6379"
```

## Start Local Services

Start Postgres and MinIO:

```powershell
docker compose up -d
```

Postgres runs on:

```text
localhost:9000
```

MinIO runs on:

```text
API:     http://localhost:9001
Console: http://localhost:9002
```

Default MinIO credentials:

```text
Username: minioadmin
Password: minioadmin123
```

Create these MinIO buckets:

```text
projects
starter-projects
```

The project creation flow expects starter files under:

```text
starter-projects/react-vite-tailwind-daisyui-starter/
```

## Start Kubernetes Resources

Check that Kubernetes is running:

```powershell
kubectl get nodes
```

Build the preview proxy image:

```powershell
docker build -t shuttle-proxy:latest .\proxy
```

Apply the Kubernetes manifests:

```powershell
kubectl apply -f k8s\infra.yml
kubectl apply -f k8s\runner-pods.yml
kubectl apply -f k8s\policy.yml
kubectl apply -f k8s\codepilot-proxy.yml
```

Check the pods:

```powershell
kubectl get pods -n codepilot-apps
kubectl get svc -n codepilot-apps
```

Expected pods:

```text
redis-server
runner-pool
codepilot-proxy
```

## Port Forward Kubernetes Services

Keep this running in one terminal so the Spring Boot app can write preview routes to Redis:

```powershell
kubectl port-forward -n codepilot-apps svc/redis-service 6379:6379
```

Keep this running in another terminal so the browser can reach the preview proxy:

```powershell
kubectl port-forward -n codepilot-apps svc/codepilot-proxy-svc 8090:80
```

## Run Spring Boot

Start the app:

```powershell
.\gradlew.bat bootRun
```

The API runs on:

```text
http://localhost:8080
```

## Postman Flow

### 1. Sign Up

```http
POST http://localhost:8080/api/auth/signup
Content-Type: application/json
```

Body:

```json
{
  "username": "test@example.com",
  "name": "Test User",
  "password": "test1234"
}
```

Save the `token` from the response.

### 2. Create Project

```http
POST http://localhost:8080/api/projects
Authorization: Bearer <token>
Content-Type: application/json
```

Body:

```json
{
  "name": "Preview Test"
}
```

Save the returned project `id`.

### 3. Deploy Project

```http
POST http://localhost:8080/api/projects/{projectId}/deploy
Authorization: Bearer <token>
```

Body: none.

Expected response:

```json
{
  "previewUrl": "http://project-4.localtest.me:8090"
}
```

The project number in the URL changes based on the project id.

### 4. Open Preview

Open the returned `previewUrl` in a browser:

```text
http://project-4.localtest.me:8090
```

`localtest.me` resolves to `127.0.0.1`, so no Windows hosts file entry is needed.

## Useful Debug Commands

Check Redis route for a preview:

```powershell
kubectl exec -n codepilot-apps deploy/redis-server -- redis-cli GET route:project-4.localtest.me
```

List all preview routes:

```powershell
kubectl exec -n codepilot-apps deploy/redis-server -- redis-cli KEYS "route:*"
```

Check runner pods and labels:

```powershell
kubectl get pods -n codepilot-apps --show-labels
```

Check proxy logs:

```powershell
kubectl logs -n codepilot-apps deploy/codepilot-proxy
```

Check runner logs:

```powershell
kubectl logs -n codepilot-apps deploy/runner-pool -c runner
kubectl logs -n codepilot-apps deploy/runner-pool -c syncer
```

## Common Issues

If `http://127.0.0.1:8090` says preview not found, that is expected. Use the generated preview URL, for example:

```text
http://project-4.localtest.me:8090
```

If deploy cannot find idle runners, check:

```powershell
kubectl get pods -n codepilot-apps --show-labels
```

If the proxy pod has an image error, rebuild the image and restart the deployment:

```powershell
docker build -t shuttle-proxy:latest .\proxy
kubectl rollout restart deployment codepilot-proxy -n codepilot-apps
```

If the preview route is missing in Redis, make sure the Redis port-forward is running before calling deploy:

```powershell
kubectl port-forward -n codepilot-apps svc/redis-service 6379:6379
```
