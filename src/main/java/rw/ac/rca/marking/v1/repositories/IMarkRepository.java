package rw.ac.rca.marking.v1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.v1.models.Course;
import rw.ac.rca.marking.v1.models.Mark;
import rw.ac.rca.marking.v1.models.Student;

import java.util.UUID;

@Repository
public interface IMarkRepository extends JpaRepository<Mark, UUID> {

    Page<Mark> findByStudent(Student student, Pageable pageable);

    Page<Mark> findByCourse(Course course, Pageable pageable);

}
