package rw.ac.rca.marking.v1.dtos;

import rw.ac.rca.marking.v1.security.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ResetPasswordDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String activationCode;

    @ValidPassword
    private String password;
}
