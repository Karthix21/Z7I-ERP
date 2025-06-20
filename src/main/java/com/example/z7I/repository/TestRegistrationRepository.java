package com.example.z7I.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.z7I.model.TestRegistration;

public interface TestRegistrationRepository extends JpaRepository<TestRegistration, Long> {    
    @Query("SELECT tr FROM TestRegistration tr WHERE tr.registrationId = :registrationId")
    TestRegistration findByRegistrationId(@Param("registrationId") String registrationId);

    @Query("SELECT tr FROM TestRegistration tr WHERE " +
           "LOWER(tr.student.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
           "LOWER(tr.student.email) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    Page<TestRegistration> findByStudentNameOrEmailContainingIgnoreCase(
        @Param("searchText") String searchText,
        Pageable pageable);

    @Query("SELECT tr FROM TestRegistration tr WHERE " +
           "(:searchText IS NULL OR LOWER(tr.student.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(tr.student.email) LIKE LOWER(CONCAT('%', :searchText, '%'))) AND " +
           "(:startDate IS NULL OR tr.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR tr.createdAt <= :endDate)")
    Page<TestRegistration> findByFilters(
        @Param("searchText") String searchText,
        @Param("startDate") java.time.LocalDateTime startDate,
        @Param("endDate") java.time.LocalDateTime endDate,
        Pageable pageable);
        
    @Query("SELECT tr FROM TestRegistration tr WHERE tr.paymentStatus = :paymentStatus")
    Page<TestRegistration> findByPaymentStatus(
        @Param("paymentStatus") Boolean paymentStatus,
        Pageable pageable);
        
    @Query("SELECT tr FROM TestRegistration tr WHERE " +
           "(LOWER(tr.student.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
           "LOWER(tr.student.email) LIKE LOWER(CONCAT('%', :searchText, '%'))) AND " +
           "tr.paymentStatus = :paymentStatus")
    Page<TestRegistration> findByStudentNameOrEmailContainingIgnoreCaseAndPaymentStatus(
        @Param("searchText") String searchText,
        @Param("paymentStatus") Boolean paymentStatus,
        Pageable pageable);
}
