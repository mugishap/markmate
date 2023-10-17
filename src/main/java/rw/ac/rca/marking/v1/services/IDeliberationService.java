package rw.ac.rca.marking.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.marking.v1.models.Deliberation;

import java.util.UUID;

public interface IDeliberationService {

    Deliberation create(Deliberation deliberation);

    Deliberation update(UUID id, Deliberation deliberation);

    Page<Deliberation> findAll(Pageable pageable);

    String delete(UUID id);

}
