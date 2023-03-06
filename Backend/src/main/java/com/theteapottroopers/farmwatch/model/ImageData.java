package com.theteapottroopers.farmwatch.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * This class contains all Image Data.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String type;
    @Lob // Needed to store BINARY FORMAT
    @Column(columnDefinition="LONGBLOB")
    private byte[] imageData;
}
