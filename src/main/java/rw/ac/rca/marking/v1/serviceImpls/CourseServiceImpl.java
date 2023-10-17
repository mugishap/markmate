package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.exceptions.ResourceNotFoundException;
import rw.ac.rca.marking.v1.models.Course;
import rw.ac.rca.marking.v1.repositories.ICourseRepository;
import rw.ac.rca.marking.v1.services.ICourseService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return this.courseRepository.findAll(pageable);
    }

    @Override
    public Course findById(UUID id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    }

    @Override
    public Course update(UUID id, Course course) {
        Course entity = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        entity.setName(course.getName());
        entity.setStatus(course.getStatus());
        entity.setWeight(course.getWeight());
        entity.setCode(course.getCode());
        entity.setWeight(course.getWeight());
        return this.courseRepository.save(entity);
    }

    @Override
    public String delete(UUID id) {
        this.courseRepository.deleteById(id);
        return "Course Deleted successfully";
    }
}
