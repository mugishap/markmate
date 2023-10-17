package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateCourseDTO;
import rw.ac.rca.marking.v1.models.Course;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.services.ICourseService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/courses")
public class CourseController {

    private final ICourseService courseService;

    @PostMapping("/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateCourseDTO dto
    ) {
        Course course = new Course();
        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setWeight(dto.getWeight());
        course.setPassMark(dto.getPassMark());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Course created successfully", this.courseService.create(course)));
    }

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok(ApiResponse.success("Courses fetched successfully", this.courseService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponse> findById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(ApiResponse.success("Course fetched successfully", this.courseService.findById(id)));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ApiResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid CreateCourseDTO dto
    ) {
        Course course = this.courseService.findById(id);
        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setWeight(dto.getWeight());
        course.setPassMark(dto.getPassMark());
        return ResponseEntity.ok(ApiResponse.success("Course updated successfully", this.courseService.update(course)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponse> delete(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(ApiResponse.success("Course deleted successfully", this.courseService.delete(id)));
    }

}
