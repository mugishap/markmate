package rw.ac.rca.marking.v1.dtos;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class CreateCourseDTO {

    @NotBlank
    @Min(value = 8,message = "Course code must be 8 characters")
    @Max(value = 8,message = "Course code must be 8 characters")
    private String code;

    @NotBlank
    @Min(value = 3,message = "Course name must be at least 3 characters")
    @Max(value = 75,message = "Course name must be at most 75 characters")
    private String name;

    @NotBlank
    @Min(value = 1,message = "Course weight must be at least 1")
    private Double weight;

    @NotBlank
    @Min(value = 1,message = "Course pass mark must be at least 1")
    private Double passMark;

}
