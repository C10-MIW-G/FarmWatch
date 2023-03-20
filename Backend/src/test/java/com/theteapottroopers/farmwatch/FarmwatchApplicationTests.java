package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import com.theteapottroopers.farmwatch.validation.AnimalValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.service.AnimalService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FarmwatchApplicationTests {

	@Mock
	private AnimalRepository animalRepository;

	@InjectMocks
	private AnimalService animalService;

	@Autowired
	FileStorageService fileStorageService;
	@Autowired
	AnimalValidation animalValidation;

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

	@Test
	@DisplayName("findAnimalById should not throw exception")
	public void testFindAnimalByIdWithAnimal() {
		Animal testAnimal = new Animal();
		when(animalRepository.findById(1L)).thenReturn(Optional.of(testAnimal));

		assertDoesNotThrow(() -> {
			animalService.findAnimalById(1L);
		});
	}

	@Test
	@DisplayName("findAnimalById should throw exception")
	public void testFindAnimalByIdWithoutAnimal() {

		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.findAnimalById(1L);
		});
	}

	@Test
	@DisplayName("findAllAnimals should not throw exception")
	public void testFindAllAnimalsWithAnimals() {
		List<Animal> animals = new ArrayList<>();
		animals.add( new Animal());
		animals.add( new Animal());
		animals.add( new Animal());

		when(animalRepository.findAll()).thenReturn(animals);

		assertDoesNotThrow(() -> {
			animalService.findAllAnimals();
		});
	}

	@Test
	@DisplayName("findAllAnimals should throw exception")
	public void testFindAllAnimalsWithNoAnimals() {
		when(animalRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.findAllAnimals();
		});
	}

}
