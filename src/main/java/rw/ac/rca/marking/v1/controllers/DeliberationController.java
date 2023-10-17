package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateDeliberationDTO;
import rw.ac.rca.marking.v1.dtos.UpdateDeliberationDTO;
import rw.ac.rca.marking.v1.models.Deliberation;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.services.IDeliberationService;
import rw.ac.rca.marking.v1.services.IStudentService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/deliberations")
public class DeliberationController {

    private final IDeliberationService deliberationService;
    private final IStudentService studentService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('TEACHER')")
    private ResponseEntity<ApiResponse> create(@RequestBody @Valid CreateDeliberationDTO dto) {
        Deliberation deliberation = new Deliberation();
        deliberation.setTotalScore(dto.getTotalScore());
        deliberation.setDecision(dto.getDecision());
        deliberation.setPosition(dto.getPosition());
        deliberation.setTotalOutOf(dto.getTotalOutOf());
        deliberation.setStudent(this.studentService.findById(dto.getStudentId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Deliberation created Successfully", this.deliberationService.create(deliberation)));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    private ResponseEntity<ApiResponse> update(@PathVariable("id") UUID id, @RequestBody @Valid UpdateDeliberationDTO dto) {
        Deliberation deliberation = new Deliberation();
        deliberation.setTotalScore(dto.getTotalScore());
        deliberation.setDecision(dto.getDecision());
        deliberation.setPosition(dto.getPosition());
        deliberation.setTotalOutOf(dto.getTotalOutOf());
        return ResponseEntity.ok().body(ApiResponse.success("Deliberation updated Successfully", this.deliberationService.update(deliberation)));
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('TEACHER')")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Deliberations fetched Successfully", this.deliberationService.findAll(pageable)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Deliberation deleted Successfully", this.deliberationService.delete(id)));
    }

}
