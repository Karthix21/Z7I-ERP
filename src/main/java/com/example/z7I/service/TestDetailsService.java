package com.example.z7I.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.z7I.dto.TestDetailsResponse;
import com.example.z7I.model.TestDetails;
import com.example.z7I.repository.TestDetailsRepository;

@Service
public class TestDetailsService {

    private final TestDetailsRepository testDetailsRepository;

    public TestDetailsService(TestDetailsRepository testDetailsRepository) {
        this.testDetailsRepository = testDetailsRepository;
    }

    public TestDetailsResponse getTestDetailsByClass(Integer classId) {
        TestDetailsResponse response = new TestDetailsResponse();
        List<TestDetails> testDetailsList = testDetailsRepository.findByClassIdAndTestType(classId, "general");
        if (testDetailsList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("No test details found for this class");
            return response;
        }
        response.setSuccess(true);
        response.setMessage("Test details found");
        response.setData(testDetailsList.stream().map(this::mapToData).collect(Collectors.toList()));
        return response;
    }

    public TestDetailsResponse getRenaissanceTestDetailsByClass(Integer classId) {
        TestDetailsResponse response = new TestDetailsResponse();
        List<TestDetails> testDetailsList = testDetailsRepository.findByClassIdAndTestType(classId, "renaissance");
        if (testDetailsList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("No test details found for this class");
            return response;
        }
        response.setSuccess(true);
        response.setMessage("Test details found");
        response.setData(testDetailsList.stream().map(this::mapToData).collect(Collectors.toList()));
        return response;
    }

    private TestDetailsResponse.TestDetailsData mapToData(TestDetails testDetails) {
        TestDetailsResponse.TestDetailsData data = new TestDetailsResponse.TestDetailsData();
        data.setId(testDetails.getId());
        data.setClassId(testDetails.getClassId());
        data.setTestDate(testDetails.getTestDate() != null ? testDetails.getTestDate().toString() : null);
        data.setTestMode(testDetails.getTestMode());
        data.setTestCity(testDetails.getTestCity());
        data.setTestCentre(testDetails.getTestCentre());
        data.setStudyCentre(testDetails.getStudyCentre());
        data.setProgramName(testDetails.getProgramName());
        data.setTestType(testDetails.getTestType());
        data.setStudyWish(testDetails.getStudyWish());
        return data;
    }
}
