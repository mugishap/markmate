package rw.ac.rca.marking.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.v1.models.Student;

import java.util.UUID;

@Repository
public interface IStudentRepository extends JpaRepository<Student, UUID> {
}
