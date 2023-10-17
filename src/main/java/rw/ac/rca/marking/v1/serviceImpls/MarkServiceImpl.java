package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.models.Course;
import rw.ac.rca.marking.v1.models.Mark;
import rw.ac.rca.marking.v1.models.Student;
import rw.ac.rca.marking.v1.repositories.IMarkRepository;
import rw.ac.rca.marking.v1.services.IMarkService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements IMarkService {

    private final IMarkRepository markRepository;

    @Override
    public Mark create(Mark mark) {
        return this.markRepository.save(mark);
    }

    @Override
    public Page<Mark> findAll(Pageable pageable) {
        return this.markRepository.findAll(pageable);
    }

    @Override
    public Page<Mark> findByStudent(Student student, Pageable pageable) {
        return this.markRepository.findByStudent(student, pageable);
    }

    @Override
    public Page<Mark> findByCourse(Course course, Pageable pageable) {
        return this.markRepository.findByCourse(course, pageable);
    }

    @Override
    public Mark update(UUID id, Mark mark) {
        Mark entity = this.markRepository.getById(id);
        entity.setScore(mark.getScore());
        entity.setPassMark(mark.getPassMark());
        entity.setOutOf(mark.getOutOf());
        entity.setDecision(mark.getDecision());
        return this.markRepository.save(entity);
    }

    @Override
    public String delete(UUID id) {
        this.markRepository.deleteById(id);
        return "Mark deleted successfully";
    }
}
