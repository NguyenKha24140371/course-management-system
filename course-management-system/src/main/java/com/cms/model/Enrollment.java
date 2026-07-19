package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Entity lưu thông tin đăng ký khóa học
@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    // Khóa chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sinh viên đăng ký
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Khóa học đăng ký
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Thời gian đăng ký
    @Builder.Default
    private LocalDateTime enrollmentDate = LocalDateTime.now();

    // Trạng thái đăng ký
    @Builder.Default
    private String status = "ENROLLED";
}