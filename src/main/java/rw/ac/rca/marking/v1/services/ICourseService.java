package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Course;

import java.util.UUID;

public interface ICourseService {

    Course create(Course course);

    Page<Course> findAll(Pageable pageable);

    Course findById(UUID id);

    Course update(Course course);

    String delete(UUID id);

}
