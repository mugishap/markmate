package rw.ac.rca.marking.v1.dtos;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class UpdateNIDDTO {

    @NotBlank
    @Min(16)
    @Max(16)
    private String nationalId;

}
