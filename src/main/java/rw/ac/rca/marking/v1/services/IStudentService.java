package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Student;
import rw.ac.rca.marking.v1.models.Teacher;

import java.util.UUID;

public interface IStudentService {

    Student create(Student student);

    Student updateRegistrationNumber(Student student);

    Page<Student> findAll(Pageable pageable);

    Student findById(UUID id);

}
