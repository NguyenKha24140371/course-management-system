package com.cms.service;

import com.cms.dto.request.AssignmentCreateRequest;
import com.cms.model.Assignment;
import com.cms.model.Course;
import com.cms.repository.AssignmentRepository;
import com.cms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Xử lý nghiệp vụ liên quan đến bài tập
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public AssignmentService(
            AssignmentRepository assignmentRepository,
            CourseRepository courseRepository) {

        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    // Tạo bài tập mới
    public Assignment createAssignment(AssignmentCreateRequest request) {

        // Kiểm tra khóa học tồn tại
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Tạo bài tập
        Assignment assignment = Assignment.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .course(course)
                .build();

        return assignmentRepository.save(assignment);
    }

    // Lấy tất cả bài tập
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Lấy bài tập theo ID
    public Assignment getAssignment(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    // Cập nhật bài tập
    public Assignment updateAssignment(Long id, Assignment assignment) {

        Assignment oldAssignment = getAssignment(id);

        oldAssignment.setTitle(assignment.getTitle());
        oldAssignment.setDescription(assignment.getDescription());
        oldAssignment.setDueDate(assignment.getDueDate());

        return assignmentRepository.save(oldAssignment);
    }

    // Xóa bài tập
    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

    // Lấy bài tập theo khóa học
    public List<Assignment> getAssignmentsByCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return assignmentRepository.findByCourse(course);
    }
}