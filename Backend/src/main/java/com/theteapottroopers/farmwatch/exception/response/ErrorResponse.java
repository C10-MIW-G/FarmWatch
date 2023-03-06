package com.theteapottroopers.farmwatch.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this reponse will go to the front-end of the application
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String message;

}
