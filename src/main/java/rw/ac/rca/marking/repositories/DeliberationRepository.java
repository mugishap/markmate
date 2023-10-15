package rw.ac.rca.marking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.models.Deliberation;

import java.util.UUID;

@Repository
public interface DeliberationRepository extends JpaRepository<Deliberation, UUID> {
}
