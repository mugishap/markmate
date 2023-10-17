package rw.ac.rca.marking.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.ac.rca.marking.v1.models.Teacher;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, UUID> {

    @Query("UPDATE Teacher t SET t.nationalId=:nationalId WHERE t.id=:id")
    boolean updateTeacherNationalId(UUID id, String nationalId);

    Optional<Teacher> findByEmailOrTelephoneOrNationalId(String email, String telephone, String nationalId);
}
