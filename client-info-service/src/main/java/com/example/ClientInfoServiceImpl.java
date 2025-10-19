package com.example;

import com.example.common.ClientInfo;
import com.example.common.ClientInfoServiceGrpc;
import com.example.common.ClientRequest;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClientInfoServiceImpl extends ClientInfoServiceGrpc.ClientInfoServiceImplBase {

    private final Map<String, ClientInfo> clientDatabase = new HashMap<>();
    private final Random random = new Random();

    public ClientInfoServiceImpl() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Создаем 50 реалистичных клиентов
        String[] firstNames = {"Иван", "Мария", "Алексей", "Екатерина", "Дмитрий", "Анна", "Сергей", "Ольга", "Андрей", "Наталья"};
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Попов", "Васильев", "Фёдоров", "Михайлов", "Новиков"};
        String[] occupations = {"Программист", "Врач", "Учитель", "Инженер", "Бухгалтер", "Менеджер", "Юрист", "Архитектор", "Дизайнер", "Аналитик"};

        int clientCount = 1;
        
        for (String firstName : firstNames) {
            for (String lastName : lastNames) {
                if (clientCount > 50) break;
                
                String clientId = "CLIENT_" + String.format("%03d", clientCount);
                int age = 25 + random.nextInt(40); // 25-65 лет
                String passportNumber = "45" + (10 + random.nextInt(90)) + " " + String.format("%06d", random.nextInt(1000000));
                int requestCount = random.nextInt(15);
                String occupation = occupations[random.nextInt(occupations.length)];
                
                ClientInfo clientInfo = ClientInfo.newBuilder()
                    .setClientId(clientId)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setAge(age)
                    .setPassportNumber(passportNumber)
                    .setRequestCount(requestCount)
                    .build();
                
                clientDatabase.put(clientId, clientInfo);
                clientCount++;
            }
        }
        
        System.out.println("In-memory database initialized with " + clientDatabase.size() + " clients");
        System.out.println("Available clients: CLIENT_001 to CLIENT_050");
    }

    @Override
    public void getClientInfo(ClientRequest request, 
                             StreamObserver<ClientInfo> responseObserver) {
        String clientId = request.getClientId();
        
        System.out.println("Received request for client: " + clientId);
        
        ClientInfo clientInfo = clientDatabase.get(clientId);
        
        if (clientInfo == null) {
            // Если клиент не найден
            clientInfo = ClientInfo.newBuilder()
                    .setClientId(clientId)
                    .setFirstName("Неизвестно")
                    .setLastName("Неизвестно")
                    .setAge(0)
                    .setPassportNumber("Неизвестно")
                    .setRequestCount(0)
                    .build();
        }
        
        responseObserver.onNext(clientInfo);
        responseObserver.onCompleted();
        
        System.out.println("Sent response for client: " + clientId);
    }
}
