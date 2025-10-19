package com.example;

import com.example.common.ClientInfo;
import com.example.common.ClientInfoServiceGrpc;
import com.example.common.ClientRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientInfoServiceImpl extends ClientInfoServiceGrpc.ClientInfoServiceImplBase {

    private final ClientRepository clientRepository;

    public ClientInfoServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void getClientInfo(ClientRequest request, 
                             StreamObserver<ClientInfo> responseObserver) {
        String clientId = request.getClientId();
        
        System.out.println("Received request for client: " + clientId);
        
        Optional<ClientEntity> clientOpt = clientRepository.findByClientId(clientId);
        ClientInfo clientInfo;
        
        if (clientOpt.isEmpty()) {
            // Клиент не найден
            clientInfo = ClientInfo.newBuilder()
                    .setClientId(clientId)
                    .setFirstName("Неизвестно")
                    .setLastName("Неизвестно")
                    .setAge(0)
                    .setPassportNumber("Неизвестно")
                    .setRequestCount(0)
                    .build();
        } else {
            // Клиент найден - увеличиваем счетчик запросов
            ClientEntity client = clientOpt.get();
            client.setRequestCount(client.getRequestCount() + 1);
            clientRepository.save(client);
            
            clientInfo = ClientInfo.newBuilder()
                    .setClientId(client.getClientId())
                    .setFirstName(client.getFirstName())
                    .setLastName(client.getLastName())
                    .setAge(client.getAge())
                    .setPassportNumber(client.getPassportNumber())
                    .setRequestCount(client.getRequestCount())
                    .build();
        }
        
        responseObserver.onNext(clientInfo);
        responseObserver.onCompleted();
        
        System.out.println("Sent response for client: " + clientId);
    }
}
