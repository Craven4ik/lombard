package com.kursovaya.lombard.controllers;

import com.kursovaya.lombard.entitys.User;
import com.kursovaya.lombard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (!user.getPassword().equals(user.getCheckPassword())) {
            model.addAttribute("errorConfPassword", true);
            return "register";
        }
        if (user.getPassword().length() < 3) {
            model.addAttribute("errorLenPassword", true);
            return "register";
        }
        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorAlreadyExistsUsername", true);
            return "register";
        }
        try {
            userService.saveUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorAnomaly", true);
            return "register";
        }
    }
}
