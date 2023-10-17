package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateStudentDTO;
import rw.ac.rca.marking.v1.dtos.UpdateNIDDTO;
import rw.ac.rca.marking.v1.dtos.UpdateRegistrationNumberDTO;
import rw.ac.rca.marking.v1.exceptions.BadRequestException;
import rw.ac.rca.marking.v1.models.Role;
import rw.ac.rca.marking.v1.models.Student;
import rw.ac.rca.marking.v1.models.User;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.repositories.IRoleRepository;
import rw.ac.rca.marking.v1.services.IStudentService;
import rw.ac.rca.marking.v1.services.IUserService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/students")
public class StudentController {


    private final IStudentService studentService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleRepository roleRepository;
    private final IUserService userService;

    @PostMapping("/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateStudentDTO dto
    ) {
        Student user = new Student();
        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        Role role = roleRepository.findByName(dto.getRole()).orElseThrow(
                () -> new BadRequestException("User Role not set"));
        user.setEmail(dto.getEmail());
        user.setNames(dto.getNames());
        user.setGender(dto.getGender());
        user.setTelephone(dto.getTelephone());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singleton(role));

        return ResponseEntity.ok(new ApiResponse(true, "Student created successfully", studentService.create(user)));
    }

    @PostMapping("/update-regnumber")
    private ResponseEntity<ApiResponse> updateRegistrationNumber(
            @RequestBody @Valid UpdateRegistrationNumberDTO dto
    ) {
        User user = this.userService.getLoggedInUser();
        Student student = this.studentService.findById(user.getId());
        student.setRegistrationNumber(dto.getRegistrationNumber());
        return ResponseEntity.ok(new ApiResponse(true, "Student updated successfully", studentService.updateRegistrationNumber(student)));
    }

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok(new ApiResponse(true, "Students fetched successfully", studentService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponse> findById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(new ApiResponse(true, "Student fetched successfully", studentService.findById(id)));
    }


}
