package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateMarkDTO;
import rw.ac.rca.marking.v1.dtos.UpdateMarkDTO;
import rw.ac.rca.marking.v1.models.Mark;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.services.ICourseService;
import rw.ac.rca.marking.v1.services.IMarkService;
import rw.ac.rca.marking.v1.services.IStudentService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/marks")
public class MarkController {

    private final IMarkService markService;
    private final ICourseService courseService;
    private final IStudentService studentService;

    @PostMapping("/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateMarkDTO dto
    ) {
        Mark mark = new Mark();
        mark.setPassMark(dto.getPassMark());
        mark.setScore(dto.getScore());
        mark.setOutOf(dto.getOutOf());
        mark.setCourse(this.courseService.findById(dto.getCourseId()));
        mark.setDecision(dto.getDecision());
        mark.setStudent(this.studentService.findById(dto.getStudentId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Mark created Successfully", this.markService.create(mark)));
    }

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok().body(ApiResponse.success("Marks retrieved Successfully", this.markService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponse> findById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success("Mark retrieved Successfully", this.markService.findById(id)));
    }

    @GetMapping("/student/{studentId}")
    private ResponseEntity<ApiResponse> findByStudent(
            @PathVariable("studentId") UUID studentId,
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit

    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok().body(ApiResponse.success("Marks retrieved Successfully", this.markService.findByStudent(this.studentService.findById(studentId), pageable)));
    }

    @GetMapping("/course/{courseId}")
    private ResponseEntity<ApiResponse> findByCourse(
            @PathVariable("courseId") UUID courseId,
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit

    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok().body(ApiResponse.success("Marks retrieved Successfully", this.markService.findByCourse(this.courseService.findById(courseId), pageable)));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<ApiResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateMarkDTO dto
    ) {
        Mark mark = this.markService.findById(id);
        mark.setPassMark(dto.getPassMark());
        mark.setScore(dto.getScore());
        mark.setOutOf(dto.getOutOf());
        mark.setDecision(dto.getDecision());
        return ResponseEntity.ok().body(ApiResponse.success("Mark created Successfully", this.markService.update(mark)));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> delete(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success("Mark deleted Successfully", this.markService.delete(id)));
    }

}
