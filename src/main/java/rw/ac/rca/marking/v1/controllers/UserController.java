package rw.ac.rca.marking.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.marking.v1.dtos.UpdateUserDTO;
import rw.ac.rca.marking.v1.fileHandling.File;
import rw.ac.rca.marking.v1.fileHandling.FileStorageService;
import rw.ac.rca.marking.v1.models.User;
import rw.ac.rca.marking.v1.payload.ApiResponse;
import rw.ac.rca.marking.v1.services.IFileService;
import rw.ac.rca.marking.v1.services.IUserService;
import rw.ac.rca.marking.v1.utils.Constants;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final IUserService userService;
    private final FileStorageService fileStorageService;
    private final IFileService fileService;

    @Value("${uploads.directory.user_profiles}")
    private String directory;

    @GetMapping(path = "/current-user")
    public ResponseEntity<ApiResponse> currentlyLoggedInUser() {
        return ResponseEntity.ok(ApiResponse.success("Logged in user fetched successfully", userService.getLoggedInUser()));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @GetMapping(path = "/paginated")
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok(ApiResponse.success("Users fetched successfully", userService.getAll(pageable)));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(ApiResponse.success("User fetched successfully", this.userService.getById(id)));
    }

    @PutMapping(path = "/{id}/upload-profile")
    public ResponseEntity<ApiResponse> uploadProfileImage(
            @PathVariable(value = "id") UUID id,
            @RequestParam("file") MultipartFile document
    ) {
        this.userService.getById(id);
        File file = this.fileService.create(document, directory);

        User updated = this.userService.changeProfileImage(id, file);

        return ResponseEntity.ok(ApiResponse.success("File saved successfully", updated));

    }

    @GetMapping("/load-file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadProfileImage(@PathVariable String filename) {

        Resource file = this.fileStorageService.load(directory, filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid UpdateUserDTO dto
    ) {
User user = this.userService.getLoggedInUser();
        user.setNames(dto.getNames());
        user.setTelephone(dto.getTelephone());
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", this.userService.update(user)));
    }

}