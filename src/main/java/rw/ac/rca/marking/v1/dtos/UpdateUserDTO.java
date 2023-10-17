package rw.ac.rca.marking.v1.dtos;

import rw.ac.rca.marking.v1.enums.EGender;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UpdateUserDTO {
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    private String names;

    @NotBlank
    @Pattern(regexp = "[0-9]{12}")
    private String telephone;


    private EGender gender;

}