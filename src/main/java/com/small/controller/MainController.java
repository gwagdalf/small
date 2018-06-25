package com.small.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // templates/main.html 을 작성해야한다.
    @GetMapping(path="/")
    public String main(){
//        return "/items/listing";
        return "redirect:" + "/items";
    }
}
