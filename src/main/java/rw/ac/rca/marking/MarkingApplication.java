package rw.ac.rca.marking;

import rw.ac.rca.marking.v1.enums.ERole;
import rw.ac.rca.marking.v1.models.Role;
import rw.ac.rca.marking.v1.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class MarkingApplication {

    private final IRoleRepository roleRepository;

    @Autowired
    public MarkingApplication(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MarkingApplication.class, args);
    }

    @Bean
    public void registerRoles() {
        Set<ERole> roles = new HashSet<>();
        roles.add(ERole.ADMIN);
        roles.add(ERole.TEACHER);
        roles.add(ERole.STUDENT);

        for (ERole role : roles) {
            Optional<Role> roleByName = roleRepository.findByName(role);
            if (!roleByName.isPresent()) {
                Role newRole = new Role(role, role.toString());
                roleRepository.save(newRole);
                System.out.println("Created: " + role.toString());

            }
        }
    }
}
