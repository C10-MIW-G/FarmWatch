package com.theteapottroopers.farmwatch.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 *
 */
@RestController
@RequestMapping("api/v1/TestResource")
public class TestResource {
    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test is completed from secure endpoint");
    }
}
