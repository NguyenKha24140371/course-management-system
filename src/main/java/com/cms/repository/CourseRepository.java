package com.cms.repository;

import com.cms.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Tìm khóa học theo tên
    List<Course> findByTitleContainingIgnoreCase(String title);

    // Tìm kiếm có phân trang
    Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // Kiểm tra tên khóa học đã tồn tại chưa
    boolean existsByTitle(String title);

}