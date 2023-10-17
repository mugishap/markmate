package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.models.Grade;
import rw.ac.rca.marking.v1.repositories.IGradeRepository;
import rw.ac.rca.marking.v1.services.IGradeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements IGradeService {

    private final IGradeRepository gradeRepository;

    @Override
    public Grade create(Grade grade) {
        return this.gradeRepository.save(grade);
    }

    @Override
    public Page<Grade> findAll(Pageable pageable) {
        return this.gradeRepository.findAll(pageable);
    }

    @Override
    public Grade findById(UUID id) {
        return this.gradeRepository.getById(id);
    }

    @Override
    public Grade update(Grade grade) {
        return this.gradeRepository.save(grade);
    }

    @Override
    public String delete(UUID id) {
        this.gradeRepository.deleteById(id);
        return "Grade deleted successfully";
    }
}
