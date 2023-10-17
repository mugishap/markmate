package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.marking.v1.dtos.CreateTeacherDTO;
import rw.ac.rca.marking.v1.dtos.UpdateNIDDTO;
import rw.ac.rca.marking.v1.exceptions.BadRequestException;
import rw.ac.rca.marking.v1.models.Role;
import rw.ac.rca.marking.v1.models.Teacher;
import rw.ac.rca.marking.v1.models.User;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.repositories.IRoleRepository;
import rw.ac.rca.marking.v1.services.ITeacherService;
import rw.ac.rca.marking.v1.services.IUserService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

    private final ITeacherService teacherService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleRepository roleRepository;
    private final IUserService userService;

    @PostMapping("/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateTeacherDTO dto
    ) {
        Teacher user = new Teacher();
        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        Role role = roleRepository.findByName(dto.getRole()).orElseThrow(
                () -> new BadRequestException("User Role not set"));
        user.setEmail(dto.getEmail());
        user.setNames(dto.getNames());
        user.setGender(dto.getGender());
        user.setTelephone(dto.getTelephone());
        user.setNationalId(dto.getNationalId());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singleton(role));

        return ResponseEntity.ok(new ApiResponse(true, "Teacher created successfully", teacherService.create(user)));
    }

    @PostMapping("/update-nid")
    private ResponseEntity<ApiResponse> updateNationalId(
            @RequestBody @Valid UpdateNIDDTO dto
    ) {
        User user = this.userService.getLoggedInUser();
        Teacher teacher = this.teacherService.findById(user.getId());
        teacher.setNationalId(dto.getNationalId());
        return ResponseEntity.ok(new ApiResponse(true, "Teacher updated successfully", teacherService.updateNationalId(teacher)));
    }

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok(new ApiResponse(true, "Teachers fetched successfully", teacherService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponse> findById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(new ApiResponse(true, "Teacher fetched successfully", teacherService.findById(id)));
    }

}
