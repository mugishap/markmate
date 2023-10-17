package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Course;
import rw.ac.rca.marking.v1.models.Mark;
import rw.ac.rca.marking.v1.models.Student;

import java.util.UUID;

public interface IMarkService {

    Mark create(Mark mark);

    Page<Mark> findAll(Pageable pageable);
    Mark findById(UUID id);

    Page<Mark> findByStudent(Student student, Pageable pageable);

    Page<Mark> findByCourse(Course course, Pageable pageable);

    Mark update(Mark mark);

    String delete(UUID id);

}
