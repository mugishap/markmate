package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.models.Student;
import rw.ac.rca.marking.v1.repositories.IStudentRepository;
import rw.ac.rca.marking.v1.services.IStudentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student updateRegistrationNumber(String registrationNumber, UUID id) {
        this.studentRepository.updateStudentRegistrationNumber(id, registrationNumber);
        return this.studentRepository.getById(id);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }
}
