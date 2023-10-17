package rw.ac.rca.marking.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.marking.v1.audits.TimestampAudit;
import rw.ac.rca.marking.v1.enums.EDeliberationDecision;

import javax.persistence.*;
import java.util.UUID;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Deliberation extends TimestampAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Student student;

    private String academicYear;

    private Double totalScore;

    private Double totalPassMark;

    @Column(name = "total_out_of")
    private Double totalOutOf;

    private Integer position;

    @Enumerated(EnumType.STRING)
    private EDeliberationDecision decision;

}
