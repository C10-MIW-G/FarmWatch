package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * This is the File Storage Resource.
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class FileStorageResource {
    private FileStorageService fileStorageService;

    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/image")
    // @PreAuthorize("hasAnyRole('USER', 'CARETAKER','ADMIN')") Frontend not implemented yet
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException{
        String uploadImage = fileStorageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                        .body(uploadImage);
    }

    @GetMapping("/image/{fileName}")
    // @PreAuthorize("hasAnyRole('USER', 'CARETAKER','ADMIN')") Frontend not implemented yet!
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = fileStorageService.downloadImage(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
