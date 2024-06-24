package org.example.woowalearn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/page/register")
    public String getRegisterPage(){
        return "register";
    }
    @GetMapping("/page/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/page")
    public String getIndexPage(){
        return "index";
    }
}
