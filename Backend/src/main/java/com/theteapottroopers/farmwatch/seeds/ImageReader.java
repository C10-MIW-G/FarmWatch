package com.theteapottroopers.farmwatch.seeds;

import com.theteapottroopers.farmwatch.Utilities.ImageUtilities;
import com.theteapottroopers.farmwatch.model.ImageData;
import com.theteapottroopers.farmwatch.repository.StorageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Eelke Mulder
 */
public class ImageReader {

    private final StorageRepository storageRepository;

    public ImageReader(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }


    public void saveImageDataFromPath(String path, StorageRepository storageRepository) throws IOException {

        Path filePath = Paths.get(path);
        String fileName = filePath.getFileName().toString();
        String contentType = Files.probeContentType(filePath);
        byte[] imageData = Files.readAllBytes(filePath);

        storageRepository.save(ImageData.builder()
                .name(fileName)
                .type(contentType)
                .imageData(ImageUtilities.compressImage(imageData))
                .build());
    }



}
