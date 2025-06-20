package com.example.z7I.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.z7I.model.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    List<StudentProfile> findByMobileNo(String mobileNo);
}
