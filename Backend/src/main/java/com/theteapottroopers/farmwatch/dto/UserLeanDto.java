package com.theteapottroopers.farmwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * User DTO containing the Id and Name
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLeanDto {
    private Long id;
    private String name;
}

