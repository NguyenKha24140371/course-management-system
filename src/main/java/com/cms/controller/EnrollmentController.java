package com.cms.controller;

import com.cms.dto.request.EnrollmentRequest;
import com.cms.model.Enrollment;
import com.cms.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller xử lý API đăng ký khóa học
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // API đăng ký khóa học
    @PostMapping
    public ResponseEntity<Enrollment> enrollCourse(
            @Valid
            @RequestBody EnrollmentRequest request) {

        return ResponseEntity.ok(
                enrollmentService.enrollCourse(request)
        );
    }

    // API hủy đăng ký
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelEnrollment(
            @PathVariable Long id) {

        enrollmentService.cancelEnrollment(id);

        return ResponseEntity.ok("Enrollment cancelled successfully");
    }

    // API lấy danh sách khóa học của sinh viên
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                enrollmentService.getStudentEnrollments(studentId)
        );
    }

    // API lấy danh sách sinh viên trong khóa học
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseStudents(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                enrollmentService.getCourseStudents(courseId)
        );
    }

}