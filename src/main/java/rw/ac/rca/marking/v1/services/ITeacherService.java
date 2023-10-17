package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Teacher;

import java.util.UUID;

public interface ITeacherService {

    Teacher create(Teacher teacher);

    Teacher updateNationalId(String nationalId, UUID id);

    Page<Teacher> findAll(Pageable pageable);

}
