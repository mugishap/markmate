package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.exceptions.BadRequestException;
import rw.ac.rca.marking.v1.models.Teacher;
import rw.ac.rca.marking.v1.repositories.ITeacherRepository;
import rw.ac.rca.marking.v1.services.ITeacherService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherRepository teacherRepository;

    @Override
    public Teacher create(Teacher teacher) {
        Optional<Teacher> userOptional = this.teacherRepository.findByEmailOrTelephoneOrNationalId(teacher.getEmail(), teacher.getTelephone(), teacher.getNationalId());
        if (userOptional.isPresent() && (userOptional.get().getId() != teacher.getId()))
            throw new BadRequestException(String.format("Teacher with email '%s' or phone number '%s' or national id '%s' already exists", teacher.getEmail(), teacher.getTelephone(), teacher.getNationalId()));

        return this.teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateNationalId(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return this.teacherRepository.findAll(pageable);
    }

    @Override
    public Teacher findById(UUID id) {
        return this.teacherRepository.getById(id);
    }
}
