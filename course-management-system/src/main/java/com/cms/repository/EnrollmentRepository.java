package com.cms.repository;

import com.cms.model.Course;
import com.cms.model.Enrollment;
import com.cms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Repository thao tác với bảng Enrollment
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Kiểm tra sinh viên đã đăng ký khóa học chưa
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    // Lấy danh sách đăng ký theo sinh viên
    List<Enrollment> findByStudent(Student student);

    // Lấy danh sách đăng ký theo khóa học
    List<Enrollment> findByCourse(Course course);
}