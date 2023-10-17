package rw.ac.rca.marking.v1.repositories;

import rw.ac.rca.marking.v1.enums.ERole;
import rw.ac.rca.marking.v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole role);
}
