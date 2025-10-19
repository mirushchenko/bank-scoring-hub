package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Scoring gRPC Server...");
        
        Server server = ServerBuilder.forPort(9091)
                .addService(new ScoringServiceImpl())
                .addService(ProtoReflectionService.newInstance()) // Добавляем Reflection
                .build();
        
        server.start();
        System.out.println("gRPC Server started on port 9091");
        System.out.println("Reflection API enabled");
        System.out.println("Press Ctrl+C to stop");
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server...");
            server.shutdown();
            System.out.println("Server shut down");
        }));
        
        server.awaitTermination();
    }
}
