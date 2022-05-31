package com.kursovaya.lombard.service;

import com.kursovaya.lombard.entitys.Item;
import com.kursovaya.lombard.entitys.User;
import com.kursovaya.lombard.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class ItemService {
    @Autowired
    private UserService userService;

    @Autowired
    private ItemRepo itemRepo;

    @Transactional
    public void plusCountItems(User user, int itemNumber) {
        Item item;
        if (user.getItemCount(itemNumber) == 0) {
            item = new Item(user, itemNumber);
            addItem(item, user);
        } else {
            item = user.getItem(itemNumber);
            item.setItemCount(item.getItemCount() + 1);
        }
        itemRepo.save(item);
    }

    @Transactional
    public void minusCountItems(User user, int itemNumber) {
        Item item = user.getItem(itemNumber);
        switch (user.getItemCount(itemNumber)) {
            case 0:
                return;
            case 1: {
                deleteItemFromUser(item, user);
                break;
            }
            default: {
                item.setItemCount(item.getItemCount() - 1);
                itemRepo.save(item);
            }
        }
    }

    @Transactional
    public void clearCountItems(User user, int itemNumber) {
        Item item = user.getItem(itemNumber);
        switch (user.getItemCount(itemNumber)) {
            case 0:
                return;
            default: {
                deleteItemFromUser(item, user);
                break;
            }
        }
    }

    public void addItem(Item item, User user) {
        user.getList().add(item);
        saveItem(item);
    }

    @Transactional
    public void deleteItemFromUser(Item item, User user) {
        user.getList().remove(item);
        userService.resaveUser(user);
        deleteItem(item);
    }

    @Transactional
    public void saveItem(Item item) {
        itemRepo.save(item);
    }


    @Transactional
    public void deleteItem(Item item) {
        itemRepo.deleteById(item.getId());
    }

    @Transactional
    public void deleteItems(Set<Item> itemSet) {
        itemRepo.deleteAll(itemSet);
    }
}
