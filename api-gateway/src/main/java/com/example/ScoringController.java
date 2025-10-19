package com.example;

import com.example.common.ClientInfo;
import com.example.common.ClientInfoServiceGrpc;
import com.example.common.ClientRequest;
import com.example.common.ScoringRequest;
import com.example.common.ScoringResponse;
import com.example.common.ScoringServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class ScoringController {

    private ManagedChannel clientInfoChannel;
    private ManagedChannel scoringChannel;
    private ClientInfoServiceGrpc.ClientInfoServiceBlockingStub clientInfoStub;
    private ScoringServiceGrpc.ScoringServiceBlockingStub scoringStub;

    public ScoringController() {
        // Инициализируем gRPC каналы
        this.clientInfoChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        
        this.scoringChannel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        
        this.clientInfoStub = ClientInfoServiceGrpc.newBlockingStub(clientInfoChannel);
        this.scoringStub = ScoringServiceGrpc.newBlockingStub(scoringChannel);
    }

    @GetMapping("/client-scoring/{clientId}")
    public ResponseEntity<?> getClientScoring(@PathVariable String clientId) {
        try {
            System.out.println("API Gateway: Processing request for client: " + clientId);

            // 1. Получаем информацию о клиенте
            ClientRequest clientRequest = ClientRequest.newBuilder()
                    .setClientId(clientId)
                    .build();
            
            ClientInfo clientInfo = clientInfoStub.getClientInfo(clientRequest);

            // 2. Рассчитываем скоринг
            ScoringRequest scoringRequest = ScoringRequest.newBuilder()
                    .setClientInfo(clientInfo)
                    .build();
            
            ScoringResponse scoringResponse = scoringStub.calculateScore(scoringRequest);

            // 3. Формируем ответ
            Map<String, Object> response = new HashMap<>();
            response.put("clientInfo", mapClientInfo(clientInfo));
            response.put("scoringResult", mapScoringResult(scoringResponse));
            response.put("timestamp", LocalDateTime.now().toString());

            System.out.println("✅ API Gateway: Successfully processed client: " + clientId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("❌ API Gateway Error for client " + clientId + ": " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ошибка при получении данных клиента: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/clients/search")
    public ResponseEntity<?> searchClients(@RequestParam String query) {
        // Демо-реализация поиска клиентов
        String[] demoClients = {"CLIENT_001", "CLIENT_002", "CLIENT_003"};
        return ResponseEntity.ok(demoClients);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "api-gateway");
        status.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(status);
    }

    private Map<String, Object> mapClientInfo(ClientInfo clientInfo) {
        Map<String, Object> mapped = new HashMap<>();
        mapped.put("clientId", clientInfo.getClientId());
        mapped.put("firstName", clientInfo.getFirstName());
        mapped.put("lastName", clientInfo.getLastName());
        mapped.put("age", clientInfo.getAge());
        mapped.put("passportNumber", clientInfo.getPassportNumber());
        mapped.put("requestCount", clientInfo.getRequestCount());
        return mapped;
    }

    private Map<String, Object> mapScoringResult(ScoringResponse scoringResponse) {
        Map<String, Object> mapped = new HashMap<>();
        mapped.put("score", scoringResponse.getScore());
        mapped.put("riskLevel", scoringResponse.getRiskLevel());
        mapped.put("description", scoringResponse.getDescription());
        return mapped;
    }

    // Graceful shutdown
    public void destroy() {
        try {
            if (clientInfoChannel != null) {
                clientInfoChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            }
            if (scoringChannel != null) {
                scoringChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}