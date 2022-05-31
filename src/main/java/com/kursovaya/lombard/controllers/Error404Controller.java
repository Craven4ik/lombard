package com.kursovaya.lombard.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Error404Controller implements ErrorController {
    @RequestMapping("/404")
    public String handleError() {
        return "404";
    }
}
