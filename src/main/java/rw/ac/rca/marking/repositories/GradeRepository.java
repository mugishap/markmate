package rw.ac.rca.marking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.models.Grade;

import java.util.UUID;

@Repository
public interface GradeRepository extends JpaRepository<Grade, UUID> {
}
