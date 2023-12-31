package rw.ac.rca.marking.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.v1.models.Deliberation;

import java.util.UUID;

@Repository
public interface IDeliberationRepository extends JpaRepository<Deliberation, UUID> {
}
