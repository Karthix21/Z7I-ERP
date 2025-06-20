package com.example.z7I.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.z7I.model.TestDetails;

public interface TestDetailsRepository extends JpaRepository<TestDetails, Long> {
    List<TestDetails> findByClassId(Integer classId);

    List<TestDetails> findByClassIdAndTestType(Integer classId, String testType);

    List<TestDetails> findByTestType(String testType);
}
