package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.Utilities.ImageUtilities;
import com.theteapottroopers.farmwatch.exception.ImageFileNotFoundException;
import com.theteapottroopers.farmwatch.model.ImageData;
import com.theteapottroopers.farmwatch.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

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

    public ImageData findImageDataByFileName(String filename){
        return storageRepository.findByName(filename).orElseThrow(() -> new ImageFileNotFoundException(
                filename + " was not found!"));
    }

    public String generateUuid(){
        return UUID.randomUUID().toString();
    }

    public ImageData findImageDataById(Long id){
        return storageRepository.findById(id).orElseThrow(() -> new ImageFileNotFoundException(
                "Image by id " + id + " was not found!"));
    }

    public String uploadImage(MultipartFile file) throws IOException {

        String uuid = generateUuid();

        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(uuid)
                .type(file.getContentType())
                .imageData(ImageUtilities.compressImage(file.getBytes())).build());

        if(imageData != null){
            return uuid;
        }
        return "";
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] image = ImageUtilities.decompressImage(dbImageData.get().getImageData());
        return image;
    }
}
