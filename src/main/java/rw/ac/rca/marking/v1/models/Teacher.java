package rw.ac.rca.marking.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.marking.v1.enums.EGender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {

    @Column(name = "national_id", unique = true)
    private String nationalId;

    public Teacher(String email, String names, String telephone, EGender gender, String password, String nationalId) {
        super(email, names, telephone, gender, password);
        this.nationalId = nationalId;
    }

}
