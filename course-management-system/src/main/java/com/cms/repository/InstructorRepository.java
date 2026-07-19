package com.cms.repository;

import com.cms.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // Spring Data JPA sẽ tự động cung cấp các hàm CRUD cơ bản như:
    // findAll(), findById(), save(), deleteById(), existsById()
}