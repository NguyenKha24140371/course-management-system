package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Instructor extends User {

    @Column(name = "department")
    private String department; // Khoa/Bộ môn phụ trách (Ví dụ: Công nghệ phần mềm)
}