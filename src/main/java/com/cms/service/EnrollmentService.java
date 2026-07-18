package com.cms.service;

import com.cms.dto.request.EnrollmentRequest;
import com.cms.exception.BadRequestException;
import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Course;
import com.cms.model.Enrollment;
import com.cms.model.Student;
import com.cms.repository.CourseRepository;
import com.cms.repository.EnrollmentRepository;
import com.cms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Xử lý nghiệp vụ liên quan đến đăng ký khóa học
@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Đăng ký khóa học
    public Enrollment enrollCourse(EnrollmentRequest request) {

        // Kiểm tra sinh viên tồn tại
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Kiểm tra khóa học tồn tại
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Kiểm tra sinh viên đã đăng ký khóa học chưa
        if (enrollmentRepository.findByStudentAndCourse(student, course).isPresent()) {
            throw new BadRequestException("Student already enrolled");
        }

        // Tạo bản ghi đăng ký mới
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        // Lưu vào cơ sở dữ liệu
        return enrollmentRepository.save(enrollment);
    }

    // Hủy đăng ký khóa học
    public void cancelEnrollment(Long id) {

        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found");
        }

        enrollmentRepository.deleteById(id);
    }

    // Lấy danh sách khóa học của sinh viên
    public List<Enrollment> getStudentEnrollments(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return enrollmentRepository.findByStudent(student);
    }

    // Lấy danh sách sinh viên trong khóa học
    public List<Enrollment> getCourseStudents(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return enrollmentRepository.findByCourse(course);
    }

}
