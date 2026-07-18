package com.cms.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CourseResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private Integer duration;

    private String instructorName;

    private LocalDateTime createdAt;

    private List<LessonResponse> lessons;
}