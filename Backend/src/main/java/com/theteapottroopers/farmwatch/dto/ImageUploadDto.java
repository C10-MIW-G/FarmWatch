package com.theteapottroopers.farmwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageUploadDto {
    String fileName;
    MultipartFile imageFile;
}
