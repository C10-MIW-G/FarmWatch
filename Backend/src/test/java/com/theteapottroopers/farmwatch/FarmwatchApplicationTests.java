package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.service.FileStorageService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class FarmwatchApplicationTests {

	@Autowired
	FileStorageService fileStorageService;

	@Test
	void contextLoads() {
	}

	@Test
	void imageFileSizeValidationTest(){

		Path smallFilePath = Paths.get("src/test/resources/images/test_image_1mb.jpg");
		File smallFile = smallFilePath.toFile();

		String smallFileName = smallFile.getName();
		String smallFileOFileName = smallFile.getName();
		String smallFileContentType = "image/jpg";
		byte[] smallFileContent = null;
		try {
			smallFileContent = Files.readAllBytes(smallFilePath);
		} catch (final IOException e) {
		}

		MultipartFile smallMultipartFile = new MockMultipartFile(smallFileName,
				smallFileOFileName, smallFileContentType, smallFileContent);

		Path largeFilePath = Paths.get("src/test/resources/images/test_image_4mb.jpg");
		File largeFile = largeFilePath.toFile();
		String largeFileName = largeFile.getName();
		String largeFileOriginalFileName = largeFile.getName();
		String largeFileContentType = "image/jpg";
		byte[] largeFileContent = null;
		try {
			largeFileContent = Files.readAllBytes(largeFilePath);
		} catch (final IOException e) {
		}

		MultipartFile largeMultipartFile = new MockMultipartFile(largeFileName,
				largeFileOriginalFileName, largeFileContentType, largeFileContent);

		Assertions.assertEquals(true, fileStorageService.imageSizeValidation(smallMultipartFile));
		Assertions.assertEquals(false, fileStorageService.imageSizeValidation(largeMultipartFile));
	}
}
