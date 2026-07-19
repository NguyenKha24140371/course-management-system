DROP TABLE IF EXISTS `assignments`;

CREATE TABLE `assignments` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `course_id` bigint NOT NULL,
                               `title` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `description` text COLLATE utf8mb4_unicode_ci,
                               `due_date` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `fk_assignment_course` (`course_id`),
                               CONSTRAINT `fk_assignment_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


LOCK TABLES `assignments` WRITE;

INSERT INTO `assignments` VALUES (1,1,'Lab 1','Create REST API','2026-08-15 00:00:00');

UNLOCK TABLES;

DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `description` text COLLATE utf8mb4_unicode_ci,
                           `price` decimal(10,2) DEFAULT NULL,
                           `duration` int DEFAULT NULL,
                           `instructor_id` bigint NOT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `fk_course_instructor` (`instructor_id`),
                           CONSTRAINT `fk_course_instructor` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `courses` WRITE;

INSERT INTO `courses` VALUES (1,'Spring Boot Master','Learn Spring Boot from basic to advanced',3600000.00,30,1,'2026-07-18 13:27:12');

UNLOCK TABLES;

DROP TABLE IF EXISTS `enrollments`;

CREATE TABLE `enrollments` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `student_id` bigint NOT NULL,
                               `course_id` bigint NOT NULL,
                               `enrollment_date` date DEFAULT NULL,
                               `status` enum('ACTIVE','CANCELLED','COMPLETED') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `student_id` (`student_id`,`course_id`),
                               KEY `fk_enrollment_course` (`course_id`),
                               CONSTRAINT `fk_enrollment_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
                               CONSTRAINT `fk_enrollment_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


LOCK TABLES `enrollments` WRITE;

INSERT INTO `enrollments` VALUES (1,1,1,'2026-07-18','ACTIVE');

UNLOCK TABLES;

DROP TABLE IF EXISTS `grades`;

CREATE TABLE `grades` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `assignment_id` bigint NOT NULL,
                          `student_id` bigint NOT NULL,
                          `score` decimal(5,2) DEFAULT NULL,
                          `feedback` text COLLATE utf8mb4_unicode_ci,
                          `graded_at` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_grade_assignment` (`assignment_id`),
                          KEY `fk_grade_student` (`student_id`),
                          CONSTRAINT `fk_grade_assignment` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`id`) ON DELETE CASCADE,
                          CONSTRAINT `fk_grade_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `grades` WRITE;

INSERT INTO `grades` VALUES (1,1,1,9.50,'Excellent','2026-07-18 13:27:12');

UNLOCK TABLES;


DROP TABLE IF EXISTS `instructors`;

CREATE TABLE `instructors` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `user_id` bigint NOT NULL,
                               `specialization` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `experience` int DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `user_id` (`user_id`),
                               CONSTRAINT `fk_instructor_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `instructors` WRITE;

INSERT INTO `instructors` VALUES (1,2,'Java Spring Boot',5);

UNLOCK TABLES;

DROP TABLE IF EXISTS `lessons`;

CREATE TABLE `lessons` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `course_id` bigint NOT NULL,
                           `title` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `video_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `lesson_order` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_lesson_course` (`course_id`),
                           CONSTRAINT `fk_lesson_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `lessons` WRITE;

INSERT INTO `lessons` VALUES (1,1,'Introduction','https://youtube.com/demo',1);

UNLOCK TABLES;

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `user_id` bigint NOT NULL,
                            `major` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `year_level` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `user_id` (`user_id`),
                            CONSTRAINT `fk_student_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `students` WRITE;

INSERT INTO `students` VALUES (1,3,'Information Technology',3);

UNLOCK TABLES;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `role` enum('ADMIN','INSTRUCTOR','STUDENT') COLLATE utf8mb4_unicode_ci NOT NULL,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'Administrator','admin@gmail.com','123456',NULL,'ADMIN','2026-07-18 13:27:12'),(2,'Le Huynh Phuoc','phuocle@gmail.com','123456',NULL,'INSTRUCTOR','2026-07-18 13:27:12'),(3,'Le Van Teo','teoem@gmail.com','123456',NULL,'STUDENT','2026-07-18 13:27:12');

UNLOCK TABLES;
