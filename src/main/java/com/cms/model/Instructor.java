package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private String specialization;

    private Integer experience;
}