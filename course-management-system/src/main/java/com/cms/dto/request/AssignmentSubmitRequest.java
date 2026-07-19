package com.cms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO nhận dữ liệu nộp bài tập
@Data
public class AssignmentSubmitRequest {

    // ID bài tập
    @NotNull(message = "Assignment ID is required")
    private Long assignmentId;

    // ID sinh viên
    @NotNull(message = "Student ID is required")
    private Long studentId;

    // Nội dung bài làm
    private String answer;

}