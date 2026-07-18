package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private BigDecimal score;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private LocalDateTime gradedAt;
}