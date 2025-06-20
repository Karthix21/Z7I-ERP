package com.example.z7I.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.z7I.dto.TestDetailsResponse;
import com.example.z7I.service.TestDetailsService;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = {"http://localhost:3000", "https://z7i.in"})
public class  TestDetailsController {

    private final TestDetailsService testDetailsService;

    public TestDetailsController(TestDetailsService testDetailsService) {
        this.testDetailsService = testDetailsService;
    }

    @GetMapping("/details")
    public ResponseEntity<TestDetailsResponse> getTestDetailsByClass(@RequestParam int classId) {
        TestDetailsResponse response = testDetailsService.getTestDetailsByClass(classId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/RenaissanceDetails")
    public ResponseEntity<TestDetailsResponse> getRenaissanceTestDetailsByClass(@RequestParam int classId) {
        TestDetailsResponse response = testDetailsService.getRenaissanceTestDetailsByClass(classId);
        return ResponseEntity.ok(response);
    }

}
