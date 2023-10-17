package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Student;

import java.util.UUID;

public interface IStudentService {

    Student create(Student student);

    Student updateRegistrationNumber(String registrationNumber, UUID id);

    Page<Student> findAll(Pageable pageable);

}
