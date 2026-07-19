package com.cms.controller;

import com.cms.dto.request.LessonCreateRequest;
import com.cms.dto.request.LessonUpdateRequest;
import com.cms.dto.response.LessonResponse;
import com.cms.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    /**
     * Lấy danh sách bài học của một khóa học.
     *
     * GET /api/lessons/course/1
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonResponse>> getLessonsByCourse(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(
                lessonService.getLessonsByCourse(courseId)
        );
    }

    /**
     * Lấy chi tiết một bài học.
     *
     * GET /api/lessons/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLessonById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                lessonService.getLessonById(id)
        );
    }

    /**
     * Tạo bài học.
     *
     * POST /api/lessons
     */
    @PostMapping
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
     *
     * PUT /api/lessons/1
     */
    @PutMapping("/{id}")
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
     *
     * DELETE /api/lessons/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(
            @PathVariable Long id
    ) {
        lessonService.deleteLesson(id);

        return ResponseEntity.noContent().build();
    }
}