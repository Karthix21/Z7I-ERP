package com.example.z7I.dto;

import java.util.List;

public class TestDetailsResponse {
    private boolean success;
    private String message;
    private List<TestDetailsData> data;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<TestDetailsData> getData() { return data; }
    public void setData(List<TestDetailsData> data) { this.data = data; }

    public static class TestDetailsData {
        private Long id;

        private Integer classId;
        private String testDate;
        private String testMode;
        private String testCity;
        private String testCentre;
        private String studyCentre;
        private String programName;
        private String studyWish;
        private String testType;  // discriminator column to differentiate test details types
        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTestDate() { return testDate; }
        public void setTestDate(String testDate) { this.testDate = testDate; }
        public String getTestMode() { return testMode; }
        public void setTestMode(String testMode) { this.testMode = testMode; }
        public String getTestCity() { return testCity; }
        public void setTestCity(String testCity) { this.testCity = testCity; }
        public String getTestCentre() { return testCentre; }
        public void setTestCentre(String testCentre) { this.testCentre = testCentre; }
        public String getStudyCentre() { return studyCentre; }
        public void setStudyCentre(String studyCentre) { this.studyCentre = studyCentre; }
        public String getProgramName() { return programName; }
        public void setProgramName(String programName) { this.programName = programName; }

        public String getStudyWish() {
            return studyWish;
        }

        public void setStudyWish(String studyWish) {
            this.studyWish = studyWish;
        }

        public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }
    }
} 