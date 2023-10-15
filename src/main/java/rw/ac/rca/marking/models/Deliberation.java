package rw.ac.rca.marking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliberations")
public class Deliberation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
