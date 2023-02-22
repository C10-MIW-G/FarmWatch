package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.Utilities.ImageUtilities;
import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.model.ImageData;
import com.theteapottroopers.farmwatch.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Service
public class FileStorageService {

    private final StorageRepository storageRepository;

    @Autowired
    public FileStorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public ImageData findAnimalById(Long id){
        return storageRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(
                "Image by id " + id + " was not found!"));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtilities.compressImage(file.getBytes())).build());

        if(imageData != null){
            return file.getOriginalFilename() + " Was Uploaded Succesfully. File type: " + file.getContentType();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] image = ImageUtilities.decompressImage(dbImageData.get().getImageData());
        return image;
    }
}
