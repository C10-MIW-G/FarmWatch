package com.theteapottroopers.farmwatch.seeds;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Eelke Mulder
 *
 * this class reads biography's of the animals from a file
 */
@Getter
public class BiographyReader {

    private String animal1Description = "";
    private String animal2Description = "";
    private String animal3Description = "";
    private String animal4Description = "";
    private String animal5Description = "";
    private String animal6Description = "";
    private String animal7Description = "";
    private String animal8Description = "";

    public BiographyReader() {
        File file = new File("src/main/resources/biographies.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            int count = 1;
            while ((currentLine = br.readLine()) != null) {
                switch (count) {
                    case 1:
                        animal1Description = currentLine;
                        break;
                    case 2:
                        animal2Description = currentLine;
                        break;
                    case 3:
                        animal3Description = currentLine;
                        break;
                    case 4:
                        animal4Description = currentLine;
                        break;
                    case 5:
                        animal5Description = currentLine;
                        break;
                    case 6:
                        animal6Description = currentLine;
                        break;
                    case 7:
                        animal7Description = currentLine;
                        break;
                    case 8:
                        animal8Description = currentLine;
                        break;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String getAnimal1Description() {
//        return animal1Description;
//    }
//
//    public String getAnimal2Description() {
//        return animal2Description;
//    }
//
//    public String getAnimal3Description() {
//        return animal3Description;
//    }
//
//    public String getAnimal4Description() {
//        return animal4Description;
//    }
//
//    public String getAnimal5Description() {
//        return animal5Description;
//    }
//
//    public String getAnimal6Description() {
//        return animal6Description;
//    }
//
//    public String getAnimal7Description() {
//        return animal7Description;
//    }
//
//    public String getAnimal8Description() {
//        return animal8Description;
//    }
}

