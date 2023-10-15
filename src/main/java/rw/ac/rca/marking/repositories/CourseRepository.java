package rw.ac.rca.marking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.models.Course;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
}
