# Bank Scoring Hub

Microservices-based credit scoring system.

## Architecture

- **api-gateway** - Spring Boot API Gateway
- **client-info-service** - Client information service  
- **scoring-service** - Credit scoring engine
- **frontend-ui** - React frontend
- **common-proto** - Shared gRPC protobuf definitions

## Prerequisites

- Java 17+
- Maven
- Node.js 18+
- PostgreSQL (optional - use H2 for development)

## Development

### Frontend
```bash
cd frontend-ui
npm install
npm run dev
```

### Backend Services
```bash
cd api-gateway
mvn spring-boot:run
