package rw.ac.rca.marking.v1.repositories;

import rw.ac.rca.marking.v1.enums.ERole;
import rw.ac.rca.marking.v1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID userID);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrTelephone(String email, String telephone);

    List<User> findByRoles(ERole role);
    Page<User> findByRoles(Pageable pageable, ERole role);

    @Query("SELECT u FROM User u" +
            " WHERE (lower(u.names)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(u.email) LIKE ('%' || lower(:searchKey) || '%'))")
    List<User> searchUser(String searchKey);

    @Query("SELECT u FROM User u" +
            " WHERE (lower(u.names)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(u.email) LIKE ('%' || lower(:searchKey) || '%'))")
    Page<User> searchUser(Pageable pageable, String searchKey);
}
