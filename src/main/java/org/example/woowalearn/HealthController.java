package org.example.woowalearn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private static final String HEALTH_MESSAGE="OK";

    public ResponseEntity<String> health() {
        return ResponseEntity.ok(HEALTH_MESSAGE);
    }
}
