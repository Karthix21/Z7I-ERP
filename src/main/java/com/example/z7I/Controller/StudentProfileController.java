package com.example.z7I.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.z7I.dto.StudentProfileRequest;
import com.example.z7I.dto.StudentProfileResponse;
import com.example.z7I.service.StudentProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = {"http://localhost:3000", "https://z7i.in"})
@Validated
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("/profile")
    public ResponseEntity<StudentProfileResponse> getStudentProfile(@RequestParam String mobileNo) {
        StudentProfileResponse response = studentProfileService.getStudentProfilesByMobileNo(mobileNo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile")
    public ResponseEntity<StudentProfileResponse> createProfile(@Valid @RequestBody StudentProfileRequest profileRequest) {
        StudentProfileResponse response = studentProfileService.createStudentProfile(profileRequest);
        return ResponseEntity.status(201).body(response);
    }
}
