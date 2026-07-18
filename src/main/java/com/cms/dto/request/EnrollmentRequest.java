package com.cms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO nhận dữ liệu đăng ký khóa học
@Data
public class EnrollmentRequest {

    // ID sinh viên
    @NotNull(message = "Student ID is required")
    private Long studentId;

    // ID khóa học
    @NotNull(message = "Course ID is required")
    private Long courseId;

}