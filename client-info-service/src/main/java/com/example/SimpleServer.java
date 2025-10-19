package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleServer {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServer.class, args);
    }

    @Bean
    public CommandLineRunner runGrpcServer(ClientInfoServiceImpl clientInfoService) {
        return args -> {
            Server server = ServerBuilder.forPort(9090)
                    .addService(clientInfoService)
                    .addService(ProtoReflectionService.newInstance())
                    .build();
            
            server.start();
            System.out.println("gRPC Server started on port 9090");
            System.out.println("Connected to PostgreSQL database");
            
            // Блокируем поток
            Thread.currentThread().join();
        };
    }
}
