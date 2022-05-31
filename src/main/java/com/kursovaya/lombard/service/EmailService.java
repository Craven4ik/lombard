package com.kursovaya.lombard.service;

import com.kursovaya.lombard.entitys.Item;
import com.kursovaya.lombard.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendOrderMessage(User user, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        StringBuilder messageText = new StringBuilder("Thanks for your order!\n");

        for (Item item : user.getList()) {
            messageText.append(item.getItemName()).append(" | ")
                    .append(item.getItemCount())
                    .append("x | ")
                    .append(item.getPriceForOneItem() * item.getItemCount()).append("$|\n");
        }
        messageText.append("\nTotal cost: ").append(user.getOrderPrice());
        message.setText(messageText.toString());
        message.setSubject("Order");
        mailSender.send(message);
    }
}
