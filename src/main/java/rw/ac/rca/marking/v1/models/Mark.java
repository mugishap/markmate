package rw.ac.rca.marking.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.marking.v1.audits.TimestampAudit;
import rw.ac.rca.marking.v1.enums.EMarkDecision;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mark extends TimestampAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private Double score; //65

    @Column(name = "pass_mark")
    private Double passMark; //70

    @Column(name = "out_of")
    private Double outOf; //100

    @Enumerated(EnumType.STRING)
    private EMarkDecision decision;

}
