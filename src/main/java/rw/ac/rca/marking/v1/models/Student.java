package rw.ac.rca.marking.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.marking.v1.audits.TimestampAudit;
import rw.ac.rca.marking.v1.enums.EGender;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private String registrationNumber;

    public Student(String email, String names, String telephone, EGender gender, String password, String registrationNumber){
        super(email, names, telephone, gender, password);
        this.registrationNumber = registrationNumber;
    }

}
