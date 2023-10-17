package rw.ac.rca.marking.v1.dtos;

import lombok.Getter;
import rw.ac.rca.marking.v1.enums.EMarkDecision;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class UpdateMarkDTO {

    @NotBlank
    @Min(value = 0, message = "Pass mark must be greater than or equal to 0")
    private Double passMark;

    @NotBlank
    @Min(value = 0, message = "Score must be greater than or equal to 0")
    private Double score;

    @NotBlank
    @Min(value = 1, message = "Out of must be greater than 0")
    private double outOf;

    @NotBlank
    private EMarkDecision decision;

}
