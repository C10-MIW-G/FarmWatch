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
    private String animal9Description = "";
    private String animal10Description = "";
    private String animal11Description = "";
    private String animal12Description = "";
    private String animal13Description = "";
    private String animal14Description = "";
    private String animal15Description = "";
    private String animal16Description = "";
    private String animal17Description = "";
    private String animal18Description = "";
    private String animal19Description = "";
    private String animal20Description = "";
    private String animal21Description = "";
    private String animal22Description = "";
    private String animal23Description = "";
    private String animal24Description = "";
    private String animal25Description = "";
    private String animal26Description = "";
    private String animal27Description = "";

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
                    case 9:
                        animal9Description = currentLine;
                        break;
                    case 10:
                        animal10Description = currentLine;
                        break;
                    case 11:
                        animal11Description = currentLine;
                        break;
                    case 12:
                        animal12Description = currentLine;
                        break;
                    case 13:
                        animal13Description = currentLine;
                        break;
                    case 14:
                        animal14Description = currentLine;
                        break;
                    case 15:
                        animal15Description = currentLine;
                        break;
                    case 16:
                        animal16Description = currentLine;
                        break;
                    case 17:
                        animal17Description = currentLine;
                        break;
                    case 18:
                        animal18Description = currentLine;
                        break;
                    case 19:
                        animal19Description = currentLine;
                        break;
                    case 20:
                        animal20Description = currentLine;
                        break;
                    case 21:
                        animal21Description = currentLine;
                        break;
                    case 22:
                        animal22Description = currentLine;
                        break;
                    case 23:
                        animal23Description = currentLine;
                        break;
                    case 24:
                        animal24Description = currentLine;
                        break;
                    case 25:
                        animal25Description = currentLine;
                        break;
                    case 26:
                        animal26Description = currentLine;
                        break;
                    case 27:
                        animal27Description = currentLine;
                        break;

                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

