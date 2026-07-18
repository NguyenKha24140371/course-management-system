package com.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

// DTO tạo bài tập
@Data
public class AssignmentCreateRequest {

    // Tiêu đề bài tập
    @NotBlank(message = "Title is required")
    private String title;

    // Mô tả bài tập
    private String description;

    // Hạn nộp bài
    @NotNull(message = "Due date is required")
    private LocalDateTime dueDate;

    // ID khóa học
    @NotNull(message = "Course ID is required")
    private Long courseId;

}