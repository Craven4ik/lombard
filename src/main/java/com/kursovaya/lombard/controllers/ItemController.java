package com.kursovaya.lombard.controllers;

import com.kursovaya.lombard.entitys.User;
import com.kursovaya.lombard.service.ItemService;
import com.kursovaya.lombard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @GetMapping("/add_item_{number}")
    public String addItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.plusCountItems(userAuth, number);
        return "redirect:/index#{number}";
    }

    @GetMapping("/remove_item_{number}")
    public String removeItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.minusCountItems(userAuth, number);
        return "redirect:/index#{number}";
    }

    @GetMapping("/clear_item_{number}")
    public String clearItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.clearCountItems(userAuth, number);
        return "redirect:/index#{number}";
    }
}
