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
    public Grade update(UUID id, Grade grade) {
        Grade entity = this.gradeRepository.getById(id);
        entity.setGrade(grade.getGrade());
        entity.setMaxScore(grade.getMaxScore());
        entity.setMinScore(grade.getMinScore());
        return this.gradeRepository.save(entity);
    }

    @Override
    public String delete(UUID id) {
        this.gradeRepository.deleteById(id);
        return "Grade deleted successfully";
    }
}
