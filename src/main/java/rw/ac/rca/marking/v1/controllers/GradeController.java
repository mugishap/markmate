package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateGradeDTO;
import rw.ac.rca.marking.v1.models.Grade;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.services.IGradeService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/grades")
public class GradeController {

    private final IGradeService gradeService;

    @PostMapping("/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateGradeDTO dto
    ) {
        Grade grade = new Grade();
        grade.setMinScore(dto.getMinScore());
        grade.setMaxScore(dto.getMaxScore());
        grade.setGrade(dto.getGrade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Grade created Successfully", this.gradeService.create(grade)));
    }

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok().body(ApiResponse.success("Grades retrieved Successfully", this.gradeService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponse> findById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success("Grade retrieved Successfully", this.gradeService.findById(id)));
    }

    @PutMapping("/update/{id}")

    private ResponseEntity<ApiResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid CreateGradeDTO dto
    ) {
        Grade grade = this.gradeService.findById(id);
        grade.setMinScore(dto.getMinScore());
        grade.setMaxScore(dto.getMaxScore());
        grade.setGrade(dto.getGrade());
        return ResponseEntity.ok().body(ApiResponse.success("Grade updated Successfully", this.gradeService.update(grade)));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> delete(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success("Grade deleted Successfully", this.gradeService.delete(id)));
    }
}
