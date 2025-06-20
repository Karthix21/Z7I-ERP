package com.example.z7I.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
    private Long id;
    private String registrationId;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long testId;
    private String testName;
    private Boolean paymentStatus;
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder pattern methods
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final RegistrationDto dto = new RegistrationDto();

        public Builder id(Long id) {
            dto.setId(id);
            return this;
        }

        public Builder registrationId(String registrationId) {
            dto.setRegistrationId(registrationId);
            return this;
        }

        public Builder studentId(Long studentId) {
            dto.setStudentId(studentId);
            return this;
        }

        public Builder studentName(String studentName) {
            dto.setStudentName(studentName);
            return this;
        }

        public Builder studentEmail(String studentEmail) {
            dto.setStudentEmail(studentEmail);
            return this;
        }

        public Builder testId(Long testId) {
            dto.setTestId(testId);
            return this;
        }

        public Builder testName(String testName) {
            dto.setTestName(testName);
            return this;
        }

        public Builder paymentStatus(Boolean paymentStatus) {
            dto.setPaymentStatus(paymentStatus);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            dto.setCreatedAt(createdAt);
            return this;
        }

        public RegistrationDto build() {
            return dto;
        }
    }
}
