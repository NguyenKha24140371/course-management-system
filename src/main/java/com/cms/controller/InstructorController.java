package com.cms.controller;

import com.cms.model.Instructor; // Đảm bảo bạn đã có class Model này
import com.cms.service.InstructorService; // Giả định bạn dùng InstructorService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin("*") // Hỗ trợ kết nối Front-end tránh lỗi CORS
public class InstructorController {

    private final InstructorService instructorService;

    // Inject Service thông qua Constructor
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // 1. Lấy danh sách tất cả giảng viên
    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    // 2. Lấy thông tin chi tiết một giảng viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }

    // 3. Thêm mới giảng viên
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        Instructor createdInstructor = instructorService.createInstructor(instructor);
        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }

    // 4. Cập nhật thông tin giảng viên
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(
            @PathVariable Long id,
            @RequestBody Instructor instructorDetails) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDetails);
        return ResponseEntity.ok(updatedInstructor);
    }

    // 5. Xóa giảng viên
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok("Instructor deleted successfully");
    }
}