package com.cms.repository;

import com.cms.model.Course;
import com.cms.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    // Lấy toàn bộ bài học của một Course
    List<Lesson> findByCourse(Course course);

    // Lấy theo Course Id
    List<Lesson> findByCourseIdOrderByLessonOrderAsc(Long courseId);

    // Kiểm tra thứ tự bài học
    boolean existsByCourseIdAndLessonOrder(Long courseId, Integer lessonOrder);
}