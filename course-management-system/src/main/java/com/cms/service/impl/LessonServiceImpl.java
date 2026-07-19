package com.cms.service.impl;

import com.cms.dto.request.LessonCreateRequest;
import com.cms.dto.request.LessonUpdateRequest;
import com.cms.dto.response.LessonResponse;
import com.cms.model.Course;
import com.cms.model.Lesson;
import com.cms.repository.CourseRepository;
import com.cms.repository.LessonRepository;
import com.cms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<LessonResponse> getLessonsByCourse(Long courseId) {

        return lessonRepository
                .findByCourseIdOrderByLessonOrderAsc(courseId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public LessonResponse getLessonById(Long id) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        return convertToResponse(lesson);
    }

    @Override
    public LessonResponse createLesson(LessonCreateRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Lesson lesson = new Lesson();

        lesson.setTitle(request.getTitle());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setLessonOrder(request.getLessonOrder());
        lesson.setCourse(course);

        return convertToResponse(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonUpdateRequest request) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        lesson.setTitle(request.getTitle());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setLessonOrder(request.getLessonOrder());

        return convertToResponse(lessonRepository.save(lesson));
    }

    @Override
    public void deleteLesson(Long id) {

        if (!lessonRepository.existsById(id)) {
            throw new RuntimeException("Lesson not found");
        }

        lessonRepository.deleteById(id);
    }

    private LessonResponse convertToResponse(Lesson lesson) {

        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .videoUrl(lesson.getVideoUrl())
                .lessonOrder(lesson.getLessonOrder())
                .build();
    }
}