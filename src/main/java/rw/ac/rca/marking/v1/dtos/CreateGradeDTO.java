package rw.ac.rca.marking.v1.dtos;

import lombok.Getter;

@Getter
public class CreateGradeDTO {

    private Double minScore;
    private Double maxScore;
    private String grade;

}
