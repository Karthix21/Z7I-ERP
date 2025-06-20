package com.example.z7I.service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.z7I.dto.RegistrationDto;
import com.example.z7I.dto.RegistrationRequest;
import com.example.z7I.dto.RegistrationResponse;
import com.example.z7I.model.StudentProfile;
import com.example.z7I.model.TestDetails;
import com.example.z7I.model.TestRegistration;
import com.example.z7I.repository.StudentProfileRepository;
import com.example.z7I.repository.TestDetailsRepository;
import com.example.z7I.repository.TestRegistrationRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TestRegistrationService {
    private static final Logger log = LoggerFactory.getLogger(TestRegistrationService.class);

    private final TestRegistrationRepository testRegistrationRepository;
    private final TestDetailsRepository testDetailsRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final TemplateEngine templateEngine;

    public TestRegistrationService(
            TestRegistrationRepository testRegistrationRepository,
            TestDetailsRepository testDetailsRepository,
            StudentProfileRepository studentProfileRepository,
            TemplateEngine templateEngine) {
        this.testRegistrationRepository = testRegistrationRepository;
        this.testDetailsRepository = testDetailsRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.templateEngine = templateEngine;
    }

    public Page<RegistrationDto> findAll(Pageable pageable) {
        return testRegistrationRepository.findAll(pageable)
            .map(this::convertToDto);
    }

    public Page<RegistrationDto> findByStudentNameOrEmailContainingIgnoreCase(String searchText, Pageable pageable) {
        return testRegistrationRepository.findByStudentNameOrEmailContainingIgnoreCase(
            searchText, pageable).map(this::convertToDto);
    }

    public Page<RegistrationDto> findByPaymentStatus(Boolean paymentStatus, Pageable pageable) {
        return testRegistrationRepository.findByPaymentStatus(paymentStatus, pageable)
            .map(this::convertToDto);
    }

    public Page<RegistrationDto> findByStudentNameOrEmailContainingIgnoreCaseAndPaymentStatus(
            String searchText, Boolean paymentStatus, Pageable pageable) {
        return testRegistrationRepository.findByStudentNameOrEmailContainingIgnoreCaseAndPaymentStatus(
            searchText, paymentStatus, pageable).map(this::convertToDto);
    }

    public RegistrationResponse registerStudent(RegistrationRequest request) {
        RegistrationResponse response = new RegistrationResponse();

        if (request == null || request.getTestId() <= 0 || request.getStudentId() <= 0) {
            response.setSuccess(false);
            response.setMessage("Invalid testId or studentId");
            return response;
        }

        Optional<TestDetails> testOpt = testDetailsRepository.findById((long) request.getTestId());
        Optional<StudentProfile> studentOpt = studentProfileRepository.findById((long) request.getStudentId());

        if (testOpt.isEmpty() || studentOpt.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Test or Student not found");
            return response;
        }

        TestRegistration reg = new TestRegistration();
        reg.setTest(testOpt.get());
        reg.setStudent(studentOpt.get());
        reg.setPaymentStatus(true);
        reg = testRegistrationRepository.save(reg);

        // Generate formatted registration ID
        String regId = String.format("REG%03d", reg.getId());
        reg.setRegistrationId(regId);
        testRegistrationRepository.save(reg);

        response.setSuccess(true);
        response.setRegistrationId(regId);
        response.setMessage("Registration successful");
        return response;
    }

    public byte[] generateHallTicketPdf(String registrationId) {
        TestRegistration reg = testRegistrationRepository.findByRegistrationId(registrationId);
        if (reg == null) return null;

        StudentProfile student = reg.getStudent();
        TestDetails test = reg.getTest();

        String fullName = String.join(" ",
                student.getFirstName() != null ? student.getFirstName() : "",
                student.getMiddleName() != null ? student.getMiddleName() : "",
                student.getLastName() != null ? student.getLastName() : "").trim();

        String fullAddress = String.join(", ",
                student.getAddressLine1(),
                student.getAddressLine2(),
                student.getCity(),
                student.getState(),
                student.getCountry(),
                student.getPinCode()).replaceAll(",\\s*,", ",").replaceAll(",+$", "").trim();

        Map<String, Object> data = Map.of(
                "registrationId", reg.getRegistrationId(),
                "name", fullName,
                "address", fullAddress,
                "email", student.getEmail(),
                "programName", test.getProgramName(),
                "testCentre", test.getTestCentre(),
                "testDate", test.getTestDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                "examSchedule", "10:00 AM - 1:00 PM",
                "reportingTime", "9:00 AM",
                "registrationDate", reg.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
        );

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Context context = new Context();
            context.setVariables(data);

            String html = templateEngine.process("hallticket", context);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(out);
            builder.run();

            return out.toByteArray();
        } catch (Exception e) {
            log.error("Error generating hall ticket PDF for registration {}", registrationId, e);
            return null;
        }
    }

    /**
     * Converts TestRegistration entity to RegistrationDto
     */
    private RegistrationDto convertToDto(TestRegistration registration) {
        if (registration == null) {
            return null;
        }
        
        return RegistrationDto.builder()
            .id(registration.getId())
            .registrationId(registration.getRegistrationId())
            .studentId(registration.getStudent() != null ? registration.getStudent().getId() : null)
            .studentName(registration.getStudent() != null ? 
                String.format("%s %s", 
                    registration.getStudent().getFirstName(),
                    registration.getStudent().getLastName() != null ? registration.getStudent().getLastName() : "")
                    .trim() : "")
            .studentEmail(registration.getStudent() != null ? registration.getStudent().getEmail() : "")
            .testId(registration.getTest() != null ? registration.getTest().getId() : null)
            .testName(registration.getTest() != null ? registration.getTest().getProgramName() : "")
            .paymentStatus(registration.getPaymentStatus())
            .createdAt(registration.getCreatedAt())
            .build();
    }
}
