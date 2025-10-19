package com.example;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;
    
    @Column(name = "request_count")
    private Integer requestCount = 0;
    
    private String occupation;
    private Integer income;
    
    @Column(name = "has_criminal_record")
    private Boolean hasCriminalRecord = false;
    
    @Column(name = "credit_score")
    private Integer creditScore;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // Конструкторы
    public ClientEntity() {}
    
    public ClientEntity(String clientId, String firstName, String lastName, Integer age, 
                       String passportNumber, Integer requestCount, String occupation, 
                       Integer income, Boolean hasCriminalRecord, Integer creditScore) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passportNumber = passportNumber;
        this.requestCount = requestCount;
        this.occupation = occupation;
        this.income = income;
        this.hasCriminalRecord = hasCriminalRecord;
        this.creditScore = creditScore;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public String getClientId() { return clientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getAge() { return age; }
    public String getPassportNumber() { return passportNumber; }
    public Integer getRequestCount() { return requestCount; }
    public String getOccupation() { return occupation; }
    public Integer getIncome() { return income; }
    public Boolean getHasCriminalRecord() { return hasCriminalRecord; }
    public Integer getCreditScore() { return creditScore; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    public void setRequestCount(Integer requestCount) { 
        this.requestCount = requestCount; 
        this.updatedAt = LocalDateTime.now();
    }
}
