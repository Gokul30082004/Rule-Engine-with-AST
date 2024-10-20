package org.example.API.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String frontend(){
        return "frontend";

    }
}
