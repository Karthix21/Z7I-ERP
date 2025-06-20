package com.example.z7I.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.z7I.dto.StudentProfileRequest;
import com.example.z7I.dto.StudentProfileResponse;
import com.example.z7I.model.StudentProfile;
import com.example.z7I.repository.StudentProfileRepository;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public StudentProfileResponse getStudentProfilesByMobileNo(String mobileNo) {
        StudentProfileResponse response = new StudentProfileResponse();
        List<StudentProfile> profiles = studentProfileRepository.findByMobileNo(mobileNo);
        if (profiles.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("No student found with this mobile number");
            return response;
        }
        StudentProfile profile = profiles.get(0);
        response.setSuccess(true);
        response.setMessage("Student found");
        response.setData(mapToData(profile));
        return response;
    }

    public StudentProfileResponse createStudentProfile(StudentProfileRequest request) {
        StudentProfileResponse response = new StudentProfileResponse();
        // Check if mobile already exists
        List<StudentProfile> existing = studentProfileRepository.findByMobileNo(request.getMobileNo());
        if (!existing.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Student with this mobile number already exists");
            response.setData(mapToData(existing.get(0)));
            return response;
        }
        StudentProfile profile = new StudentProfile();
        profile.setFirstName(request.getFirstName());
        profile.setMiddleName(request.getMiddleName());
        profile.setLastName(request.getLastName());
        // Parse DOB string to java.sql.Date if present
        if (request.getDob() != null && !request.getDob().isEmpty()) {
            try {
                profile.setDob(Date.valueOf(request.getDob()));
            } catch (Exception e) {
                response.setSuccess(false);
                response.setMessage("Invalid DOB format. Use YYYY-MM-DD.");
                return response;
            }
        }
        profile.setMobileNo(request.getMobileNo());
        profile.setEmail(request.getEmail());
        profile.setGender(request.getGender());
        profile.setNationality(request.getNationality());
        profile.setCategory(request.getCategory());
        profile.setAddressLine1(request.getAddressLine1());
        profile.setAddressLine2(request.getAddressLine2());
        profile.setCountry(request.getCountry());
        profile.setState(request.getState());
        profile.setDistrict(request.getDistrict());
        profile.setCity(request.getCity());
        profile.setPinCode(request.getPinCode());
        profile.setCreatedAt(LocalDateTime.now());
        // Ignore retypeEmail for storage
        StudentProfile saved = studentProfileRepository.save(profile);
        response.setSuccess(true);
        response.setMessage("Student profile created successfully");
        response.setData(mapToData(saved));
        return response;
    }

    private StudentProfileResponse.StudentProfileData mapToData(StudentProfile profile) {
        StudentProfileResponse.StudentProfileData data = new StudentProfileResponse.StudentProfileData();
        data.setId(profile.getId());
        data.setFirstName(profile.getFirstName());
        data.setMiddleName(profile.getMiddleName());
        data.setLastName(profile.getLastName());
        data.setMobileNo(profile.getMobileNo());
        data.setEmail(profile.getEmail());
        data.setGender(profile.getGender());
        data.setNationality(profile.getNationality());
        data.setCategory(profile.getCategory());
        data.setAddressLine1(profile.getAddressLine1());
        data.setAddressLine2(profile.getAddressLine2());
        data.setCountry(profile.getCountry());
        data.setState(profile.getState());
        data.setDistrict(profile.getDistrict());
        data.setCity(profile.getCity());
        data.setPinCode(profile.getPinCode());
if (profile.getCreatedAt() != null) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    data.setCreatedAt(profile.getCreatedAt().format(formatter));
} else {
    data.setCreatedAt(null);
}

        return data;
    }
}
