package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String videoUrl;

    private Integer lessonOrder;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

}