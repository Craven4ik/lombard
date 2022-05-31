package com.kursovaya.lombard.controllers;

import com.kursovaya.lombard.entitys.User;
import com.kursovaya.lombard.service.EmailService;
import com.kursovaya.lombard.service.ItemService;
import com.kursovaya.lombard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/cart")
    public String showCart(Model model) {
        User user = userService.getUserAuth();
        model.addAttribute("items", user.getList());
        return "cart";
    }

    @GetMapping("/plusCart_{number}")
    public String addItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.plusCountItems(userAuth, number);
        return "redirect:/cart#{number}";
    }

    @GetMapping("/minusCart_{number}")
    public String removeItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.minusCountItems(userAuth, number);
        if (userAuth.getItemCount(number) == 0)
            return "redirect:/cart#cartTable";
        return "redirect:/cart#{number}";
    }

    @GetMapping("/clear_item_cart_{number}")
    public String clearItemWithNumber(@PathVariable int number) {
        User userAuth = userService.getUserAuth();
        itemService.clearCountItems(userAuth, number);
        return "redirect:/cart#cartTable";
    }

    @PostMapping("/cart")
    public String sendOrder(@RequestParam(name = "email") String email) {
        User user = userService.getUserAuth();
        emailService.sendOrderMessage(user, email);
        itemService.deleteItems(user.getList());
        user.getList().clear();
        return "redirect:/order-completed";
    }
}
