package com.example.z7I.Controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.z7I.dto.*;
import com.example.z7I.service.TestRegistrationService;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin(origins = {"http://localhost:3000", "https://z7i.in", "http://localhost:4200"})
public class RegistrationController {

    private final TestRegistrationService testRegistrationService;

    public RegistrationController(TestRegistrationService testRegistrationService) {
        this.testRegistrationService = testRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerStudent(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = testRegistrationService.registerStudent(request);
        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Retrieves all registrations with pagination, optional search, and payment status filter
     *
     * @param page           Page number (0-based), defaults to 0
     * @param size           Number of items per page, defaults to 10
     * @param search         Optional search text to filter by student name or email
     * @param paymentStatus  Optional filter by payment status (true for paid, false for unpaid)
     * @return Paginated list of registrations
     */
    @GetMapping("/all")
    public ResponseEntity<PagedResponse<RegistrationDto>> getAllRegistrations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean paymentStatus) {

        // Validate pagination parameters
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 0");
        }
        if (size <= 0 || size > 100) {
            throw new IllegalArgumentException("Page size must be between 1 and 100");
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RegistrationDto> registrations;
        
        if (search != null && paymentStatus != null) {
            registrations = testRegistrationService.findByStudentNameOrEmailContainingIgnoreCaseAndPaymentStatus(
                search, paymentStatus, pageRequest);
        } else if (search != null) {
            registrations = testRegistrationService.findByStudentNameOrEmailContainingIgnoreCase(
                search, pageRequest);
        } else if (paymentStatus != null) {
            registrations = testRegistrationService.findByPaymentStatus(paymentStatus, pageRequest);
        } else {
            registrations = testRegistrationService.findAll(pageRequest);
        }

        return ResponseEntity.ok(new PagedResponse<>(registrations));
    }

    @GetMapping("/hallticket")
    public ResponseEntity<byte[]> downloadHallTicket(@RequestParam String registrationId) throws IOException {
        try {
            byte[] pdfBytes = testRegistrationService.generateHallTicketPdf(registrationId);
            if (pdfBytes == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=hallticket_" + registrationId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating hall ticket", e);
        }
    }
}