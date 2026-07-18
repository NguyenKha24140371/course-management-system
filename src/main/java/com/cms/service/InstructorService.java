package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Instructor;
import com.cms.repository.InstructorRepository; // Giả định bạn đã có InstructorRepository
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    // 1. Lấy danh sách tất cả giảng viên
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // 2. Lấy thông tin chi tiết một giảng viên theo ID
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
    }

    // 3. Thêm mới giảng viên
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // 4. Cập nhật thông tin giảng viên
    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Instructor instructor = getInstructorById(id);

        // Bạn có thể set các trường tương ứng của Instructor ở đây, ví dụ:
        // instructor.setName(instructorDetails.getName());
        // instructor.setEmail(instructorDetails.getEmail());

        return instructorRepository.save(instructor);
    }

    // 5. Xóa giảng viên
    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instructor not found with id: " + id);
        }
        instructorRepository.deleteById(id);
    }
}