package com.example.z7I.dto;

public class StudentProfileResponse {
    private boolean success;
    private String message;
    private StudentProfileData data;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public StudentProfileData getData() { return data; }
    public void setData(StudentProfileData data) { this.data = data; }

    public static class StudentProfileData {
        private Long id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String mobileNo;
        private String email;
        private String gender;
        private String nationality;
        private String category;
        private String addressLine1;
        private String addressLine2;
        private String country;
        private String state;
        private String district;
        private String city;
        private String pinCode;
        private String createdAt;
        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getMiddleName() { return middleName; }
        public void setMiddleName(String middleName) { this.middleName = middleName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getMobileNo() { return mobileNo; }
        public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
        public String getNationality() { return nationality; }
        public void setNationality(String nationality) { this.nationality = nationality; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getAddressLine1() { return addressLine1; }
        public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
        public String getAddressLine2() { return addressLine2; }
        public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getDistrict() { return district; }
        public void setDistrict(String district) { this.district = district; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getPinCode() { return pinCode; }
        public void setPinCode(String pinCode) { this.pinCode = pinCode; }
        public String getCreatedAt() { return createdAt; }
public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    }
} 