package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.ImageUuidDto;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
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

    @GetMapping("/images/generate-uuid")
    public ResponseEntity<?> generateUuid(){
        String uuid = fileStorageService.generateUuid();

        ImageUuidDto imageuuiddto = new ImageUuidDto();
        imageuuiddto.setUuid(uuid);

        return new ResponseEntity<>(imageuuiddto, HttpStatus.OK);
    }

    @PostMapping("/images")
    // @PreAuthorize("hasAnyRole('USER', 'CARETAKER','ADMIN')") // Frontend not implemented yet
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file ) throws IOException{

        if(fileStorageService.imageSizeValidation(file)){
            String uuid = fileStorageService.uploadImage(file);
            ImageUuidDto imageUuidDto = new ImageUuidDto(uuid);

            if(!uuid.isEmpty()) {
                return new ResponseEntity<>(imageUuidDto, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }

        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = fileStorageService.downloadImage(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
