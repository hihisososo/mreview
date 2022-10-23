package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Course;
import org.zerock.mreview.entity.ManyToManyMember;
import org.zerock.mreview.entity.ManyToManyMovie;
import org.zerock.mreview.entity.Student;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ManyToManyTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void insertMemberAndMovie() {
        // create a student
        Student student = new Student("John Doe", 15, "8th");

        // save the student
        studentRepository.save(student);

        // create three courses
        Course course1 = new Course("Machine Learning", "ML", 12, 1500);
        Course course2 = new Course("Database Systems", "DS", 8, 800);
        Course course3 = new Course("Web Basics", "WB", 10, 0);

        // save courses
        courseRepository.saveAll(Arrays.asList(course1, course2, course3));

        // add courses to the student
        student.getCourses().addAll(Arrays.asList(course1, course2, course3));

        // update the student
        studentRepository.save(student);

    }

}