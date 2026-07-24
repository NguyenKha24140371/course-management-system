package com.cms.dto.request;

import lombok.Data;

@Data
public class LessonCreateRequest {
    private String title;          // Tiêu đề bài học
    private String content;        // Nội dung bài học
    private Integer orderIndex;    // Thứ tự bài học trong khóa
    private Long courseId;         // ID Khóa học
    private String chapterName;    // Tên chương (nếu gộp chung bài học & chương)
    private String videoUrl;
    private Integer lessonOrder;
}