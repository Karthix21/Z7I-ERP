package com.example.z7I.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_details")
public class TestDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer classId;

    private LocalDate testDate;

    private String testMode;

    private String testCity;

    private String testCentre;

    private String studyCentre;

    private String programName;

    private String studyWish;

    private String testType;  // discriminator column to differentiate test details types

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestCity() {
        return testCity;
    }

    public void setTestCity(String testCity) {
        this.testCity = testCity;
    }

    public String getTestCentre() {
        return testCentre;
    }

    public void setTestCentre(String testCentre) {
        this.testCentre = testCentre;
    }

    public String getStudyCentre() {
        return studyCentre;
    }

    public void setStudyCentre(String studyCentre) {
        this.studyCentre = studyCentre;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getTestMode() {
        return testMode;
    }

    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }

    public String getStudyWish() {
        return studyWish;
    }

    public void setStudyWish(String studyWish) {
        this.studyWish = studyWish;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}
