package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.exceptions.BadRequestException;
import rw.ac.rca.marking.v1.models.Student;
import rw.ac.rca.marking.v1.repositories.IStudentRepository;
import rw.ac.rca.marking.v1.services.IStudentService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        Optional<Student> userOptional = this.studentRepository.findByEmailOrTelephoneOrRegistrationNumber(student.getEmail(), student.getTelephone(), student.getRegistrationNumber());
        if (userOptional.isPresent() && (userOptional.get().getId() != student.getId()))
            throw new BadRequestException(String.format("Student with email '%s' or phone number '%s' or registration number '%s' already exists", student.getEmail(), student.getTelephone(), student.getRegistrationNumber()));

        return this.studentRepository.save(student);
    }

    @Override
    public Student updateRegistrationNumber(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(UUID id) {
        return this.studentRepository.getById(id);
    }
}
