package com.example.z7I.dto;

public class RegistrationResponse {
    private boolean success;
    private String registrationId;
    private String message;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getRegistrationId() { return registrationId; }
    public void setRegistrationId(String registrationId) { this.registrationId = registrationId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
} 