package rw.ac.rca.marking.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.marking.v1.enums.EGender;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {

    private String nationalId;

    public Teacher(String email, String names, String telephone, EGender gender, String password, String nationalId) {
        super(email, names, telephone, gender, password);
        this.nationalId = nationalId;
    }

}
