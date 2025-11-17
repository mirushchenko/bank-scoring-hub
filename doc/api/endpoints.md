# API Endpoints - Скоринговая система

## Базовый URL
http://localhost:8080/api

## Основные endpoints

### 1. Поиск и скоринг клиента
```http
GET /client-scoring/{clientId}
```
## Параметры:

clientId (path) - ID клиента (CLIENT_001, CLIENT_002, CLIENT_003)

Пример запроса:

```http
GET http://localhost:8080/api/client-scoring/CLIENT_001
```
## Успешный ответ (200 OK):


```
{
  "success": true,
  "clientInfo": {
    "clientId": "CLIENT_001",
    "firstName": "Иван",
    "lastName": "Петров"
  },
  "scoringResult": {
    "score": 85,
    "riskLevel": "LOW",
    "decision": "APPROVED",
    "creditLimit": 500000
  }
}
```
## Ошибка (404 Not Found):

```
{
  "success": false,
  "error": {
    "code": "CLIENT_NOT_FOUND",
    "message": "Клиент CLIENT_999 не найден"
  }
}
```
## 2. Проверка здоровья системы

```http
GET /health
```
## Пример запроса:

```http
GET http://localhost:8080/api/health
```
## Ответ (200 OK):

```
{
  "status": "UP",
  "services": {
    "apiGateway": "UP",
    "clientService": "UP", 
    "scoringService": "UP"
  },
  "timestamp": "2024-01-15T14:30:00Z"
}
```
