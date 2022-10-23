package org.zerock.mreview.repository;

import org.springframework.data.repository.CrudRepository;
import org.zerock.mreview.entity.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByNameContaining(String name);
}