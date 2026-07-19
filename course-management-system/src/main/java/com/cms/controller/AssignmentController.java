package com.cms.controller;

import com.cms.dto.request.AssignmentCreateRequest;
import com.cms.model.Assignment;
import com.cms.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller xử lý API bài tập
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // API tạo bài tập
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(
            @Valid
            @RequestBody AssignmentCreateRequest request) {

        return ResponseEntity.ok(
                assignmentService.createAssignment(request)
        );
    }

    // API lấy tất cả bài tập
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {

        return ResponseEntity.ok(
                assignmentService.getAllAssignments()
        );
    }

    // API lấy bài tập theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                assignmentService.getAssignment(id)
        );
    }

    // API cập nhật bài tập
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(
            @PathVariable Long id,
            @RequestBody Assignment assignment) {

        return ResponseEntity.ok(
                assignmentService.updateAssignment(id, assignment)
        );
    }

    // API xóa bài tập
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok("Assignment deleted successfully");
    }

    // API lấy bài tập theo khóa học
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByCourse(courseId)
        );
    }

}