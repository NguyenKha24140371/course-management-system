package com.cms.service;

import com.cms.dto.request.CourseCreateRequest;
import com.cms.dto.request.CourseUpdateRequest;
import com.cms.dto.response.CourseResponse;
import org.springframework.data.domain.Page;

public interface CourseService {

    /**
     * Lấy tất cả khóa học (có phân trang)
     */
    Page<CourseResponse> getAllCourses(int page, int size);

    /**
     * Lấy khóa học theo ID
     */
    CourseResponse getCourseById(Long id);

    /**
     * Tìm kiếm khóa học theo tên
     */
    Page<CourseResponse> searchCourses(String keyword, int page, int size);

    /**
     * Thêm khóa học
     */
    CourseResponse createCourse(CourseCreateRequest request);

    /**
     * Cập nhật khóa học
     */
    CourseResponse updateCourse(Long id, CourseUpdateRequest request);

    /**
     * Xóa khóa học
     */
    void deleteCourse(Long id);

}