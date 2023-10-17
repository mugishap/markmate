package rw.ac.rca.marking.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.marking.v1.models.Deliberation;
import rw.ac.rca.marking.v1.repositories.IDeliberationRepository;
import rw.ac.rca.marking.v1.services.IDeliberationService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliberationServiceImpl implements IDeliberationService {

    private final IDeliberationRepository deliberationRepository;

    @Override
    public Deliberation create(Deliberation deliberation) {
        return this.deliberationRepository.save(deliberation);
    }

    @Override
    public Deliberation update(UUID id, Deliberation deliberation) {
        Deliberation entity = this.deliberationRepository.getById(id);
        entity.setDecision(deliberation.getDecision());
        entity.setPosition(deliberation.getPosition());
        entity.setTotalOutOf(deliberation.getTotalOutOf());
        entity.setTotalScore(deliberation.getTotalScore());
        return this.deliberationRepository.save(entity);
    }

    @Override
    public Page<Deliberation> findAll(Pageable pageable) {
        return this.deliberationRepository.findAll(pageable);
    }

    @Override
    public String delete(UUID id) {
        this.deliberationRepository.deleteById(id);
        return "Deliberation deleted successfully";
    }
}
