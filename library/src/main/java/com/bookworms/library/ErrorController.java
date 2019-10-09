package com.bookworms.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class ErrorController {
@RequestMapping("/error")
    public String home(){
        return "Some error happend!";
    }
}

