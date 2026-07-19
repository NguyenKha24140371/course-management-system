package com.cms.service;

import com.cms.dto.request.LessonCreateRequest;
import com.cms.dto.request.LessonUpdateRequest;
import com.cms.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

    /**
     * Danh sách bài học của Course
     */
    List<LessonResponse> getLessonsByCourse(Long courseId);

    /**
     * Lấy bài học theo ID
     */
    LessonResponse getLessonById(Long id);

    /**
     * Thêm bài học
     */
    LessonResponse createLesson(LessonCreateRequest request);

    /**
     * Cập nhật bài học
     */
    LessonResponse updateLesson(Long id, LessonUpdateRequest request);

    /**
     * Xóa bài học
     */
    void deleteLesson(Long id);

}