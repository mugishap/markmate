package rw.ac.rca.marking.v1.models;


import lombok.*;
import rw.ac.rca.marking.v1.enums.ECourseStatus;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column(name = "pass_mark", nullable = false)
    private Double passMark; //70

    @Column(name = "weight", nullable = false)
    private Double weight; //100

    @Enumerated(EnumType.STRING)
    private ECourseStatus status;

    @Column(unique = true)
    private String code;


}
