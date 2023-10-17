package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.models.Teacher;
import rw.ac.rca.marking.v1.repositories.ITeacherRepository;
import rw.ac.rca.marking.v1.services.ITeacherService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherRepository teacherRepository;

    @Override
    public Teacher create(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateNationalId(String nationalId, UUID id) {
        this.teacherRepository.updateTeacherNationalId(id, nationalId);
        return this.teacherRepository.getById(id);
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return this.teacherRepository.findAll(pageable);
    }
}
