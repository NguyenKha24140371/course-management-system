package com.cms.controller;

import com.cms.dto.request.CourseCreateRequest;
import com.cms.dto.request.CourseUpdateRequest;
import com.cms.dto.response.CourseResponse;
import com.cms.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    /**
     * Lấy danh sách khóa học có phân trang.
     *
     * Ví dụ:
     * GET /api/courses?page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                courseService.getAllCourses(page, size)
        );
    }

    /**
     * Lấy chi tiết một khóa học.
     *
     * GET /api/courses/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    /**
     * Tìm kiếm khóa học theo tiêu đề.
     *
     * GET /api/courses/search?keyword=java&page=0&size=10
     */
    @GetMapping("/search")
    public ResponseEntity<Page<CourseResponse>> searchCourses(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                courseService.searchCourses(keyword, page, size)
        );
    }

    /**
     * Tạo khóa học.
     *
     * POST /api/courses
     */
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CourseCreateRequest request
    ) {
        CourseResponse response = courseService.createCourse(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Cập nhật khóa học.
     *
     * PUT /api/courses/1
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateRequest request
    ) {
        return ResponseEntity.ok(
                courseService.updateCourse(id, request)
        );
    }

    /**
     * Xóa khóa học.
     *
     * DELETE /api/courses/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id
    ) {
        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}