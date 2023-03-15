package com.theteapottroopers.farmwatch;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class FarmwatchApplicationTests {

	@Mock
	private AnimalRepository animalRepository;

	@InjectMocks
	private AnimalService animalService;

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

	@Test
	public void testFindAllAnimalsWithNoAnimals() {
		when(animalRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(AnimalNotFoundException.class, () -> {
			animalService.findAllAnimals();
		});
	}

	@Test
	public void testFindAllAnimalsWithAnimals() {
		List<Animal> animals = new ArrayList<>();
		animals.add( new Animal("Clara", "Chicken", "Galus galus domesticus",
				"asdf", LocalDate.of(2012, 5, 17), null));
		animals.add( new Animal("Benjamin", "Donkey", "Equus africanus",
				"asdf", LocalDate.of(2010, 8, 12), null));
		animals.add( new Animal("Hugo", "Horse", "Equus ferus caballus",
				"asdf", LocalDate.of(2009, 2, 21), null));

		when(animalRepository.findAll()).thenReturn(animals);

		assertDoesNotThrow(() -> {
			animalService.findAllAnimals();
		});
	}
}
