package com.cms.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LessonCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String videoUrl;

    @Min(1)
    private Integer lessonOrder;

    @NotNull
    private Long courseId;
}