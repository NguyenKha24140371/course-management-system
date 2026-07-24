package com.cms.controller;

import com.cms.dto.request.LessonCreateRequest;
import com.cms.dto.request.LessonUpdateRequest;
import com.cms.dto.response.LessonResponse;
import com.cms.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    /**
     * Lấy danh sách tất cả bài học của một khóa học.
     * Ai đã đăng nhập (Học viên, Giảng viên, Admin) đều có thể xem.
     *
     * GET /api/lessons/course/1
     */
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<List<LessonResponse>> getLessonsByCourse(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(
                lessonService.getLessonsByCourse(courseId)
        );
    }

    /**
     * Lấy danh sách bài học theo Chương (Chapter).
     *
     * GET /api/lessons/chapter/1
     */


    /**
     * Lấy chi tiết một bài học.
     *
     * GET /api/lessons/1
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<LessonResponse> getLessonById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                lessonService.getLessonById(id)
        );
    }

    /**
     * Tạo mới bài học.
     * Chỉ Giảng viên (INSTRUCTOR) hoặc Quản trị viên (ADMIN) mới có quyền tạo.
     *
     * POST /api/lessons
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<LessonResponse> createLesson(
            @Valid @RequestBody LessonCreateRequest request
    ) {
        LessonResponse response = lessonService.createLesson(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Cập nhật bài học.
     * Chỉ Giảng viên (INSTRUCTOR) hoặc Quản trị viên (ADMIN) mới có quyền sửa.
     *
     * PUT /api/lessons/1
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<LessonResponse> updateLesson(
            @PathVariable Long id,
            @Valid @RequestBody LessonUpdateRequest request
    ) {
        return ResponseEntity.ok(
                lessonService.updateLesson(id, request)
        );
    }

    /**
     * Xóa bài học.
     * Chỉ Giảng viên (INSTRUCTOR) hoặc Quản trị viên (ADMIN) mới có quyền xóa.
     *
     * DELETE /api/lessons/1
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<Void> deleteLesson(
            @PathVariable Long id
    ) {
        lessonService.deleteLesson(id);

        return ResponseEntity.noContent().build();
    }
}