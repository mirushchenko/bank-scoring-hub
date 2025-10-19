package com.example;

import com.example.common.ClientInfo;
import com.example.common.ScoringRequest;
import com.example.common.ScoringResponse;
import com.example.common.ScoringServiceGrpc;
import io.grpc.stub.StreamObserver;

public class ScoringServiceImpl extends ScoringServiceGrpc.ScoringServiceImplBase {

    @Override
    public void calculateScore(ScoringRequest request, 
                              StreamObserver<ScoringResponse> responseObserver) {
        ClientInfo clientInfo = request.getClientInfo();
        
        System.out.println("Calculating score for client: " + clientInfo.getClientId());
        
        // Простая логика скоринга на основе возраста и истории запросов
        int baseScore = 50;
        int ageScore = calculateAgeScore(clientInfo.getAge());
        int historyScore = calculateHistoryScore(clientInfo.getRequestCount());
        
        int totalScore = Math.min(100, baseScore + ageScore + historyScore);
        
        String riskLevel = calculateRiskLevel(totalScore);
        String description = generateDescription(totalScore, riskLevel, clientInfo);
        
        ScoringResponse response = ScoringResponse.newBuilder()
                .setClientId(clientInfo.getClientId())
                .setScore(totalScore)
                .setRiskLevel(riskLevel)
                .setDescription(description)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        
        System.out.println("Score calculated for " + clientInfo.getClientId() + ": " + totalScore + " (" + riskLevel + ")");
    }

    private int calculateAgeScore(int age) {
        if (age < 25) return 10;
        if (age <= 35) return 20;
        if (age <= 50) return 25;
        return 15; // старше 50
    }

    private int calculateHistoryScore(int requestCount) {
        if (requestCount == 0) return 5;  // новый клиент
        if (requestCount <= 3) return 15; // мало запросов - хорошо
        if (requestCount <= 7) return 10; // среднее количество
        return 5; // много запросов - возможно, проблемы
    }

    private String calculateRiskLevel(int score) {
        if (score >= 80) return "LOW";
        if (score >= 60) return "MEDIUM";
        return "HIGH";
    }

    private String generateDescription(int score, String riskLevel, ClientInfo clientInfo) {
        switch (riskLevel) {
            case "LOW":
                return "Клиент с низким уровнем риска. Надежный заемщик.";
            case "MEDIUM":
                return "Клиент со средним уровнем риска. Требуется дополнительная проверка.";
            case "HIGH":
                return "Клиент с высоким уровнем риска. Рекомендуется осторожность.";
            default:
                return "Неопределенный уровень риска. Требуется ручная проверка.";
        }
    }
}
