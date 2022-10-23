package org.zerock.mreview.repository;

import org.springframework.data.repository.CrudRepository;
import org.zerock.mreview.entity.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByTitleContaining(String title);

    List<Course> findByFeeLessThan(double fee);
}