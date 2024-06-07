package org.example.woowalearn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @RequestMapping("/health")
    String check(){
        return "OK";
    }
}
