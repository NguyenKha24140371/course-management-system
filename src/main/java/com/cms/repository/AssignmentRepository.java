package com.cms.repository;

import com.cms.model.Assignment;
import com.cms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository thao tác với bảng Assignment
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // Lấy danh sách bài tập theo khóa học
    List<Assignment> findByCourse(Course course);

}