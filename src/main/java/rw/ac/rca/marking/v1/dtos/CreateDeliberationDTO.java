package rw.ac.rca.marking.v1.dtos;

import lombok.Getter;
import rw.ac.rca.marking.v1.enums.EDeliberationDecision;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
public class CreateDeliberationDTO {

    @NotBlank(message = "Total Score is required")
    @Min(value = 0, message = "Total Score must be greater than or equal to 0")
    private Double totalScore;

    @NotBlank(message = "Decision is required")
    private EDeliberationDecision decision;

    @NotBlank
    private Integer position;

    @NotBlank
    @Min(value = 0, message = "Total Out Of must be greater than or equal to 0")
    private Double totalOutOf;

    @NotBlank
    private UUID studentId;

}
