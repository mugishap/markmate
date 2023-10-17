package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Grade;

import java.util.UUID;

public interface IGradeService {
    Grade create(Grade grade);

    Page<Grade> findAll(Pageable pageable);

    Grade findById(UUID id);

    Grade update(UUID id, Grade grade);

    String delete(UUID id);
}
