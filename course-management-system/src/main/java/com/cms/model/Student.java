package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {
    private String studentCode;
    private String major;
}