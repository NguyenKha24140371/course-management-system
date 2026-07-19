package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Entity lưu thông tin bài tập
@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {

    // Khóa chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tiêu đề
    private String title;

    // Mô tả
    @Column(columnDefinition = "TEXT")
    private String description;

    // Hạn nộp
    private LocalDateTime dueDate;

    // Khóa học chứa bài tập
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Ngày tạo
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}