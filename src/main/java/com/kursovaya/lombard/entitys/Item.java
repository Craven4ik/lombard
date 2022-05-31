package com.kursovaya.lombard.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userID;

    private int itemCount = 1;
    private int itemNumber;

    public Item(User user, int itemNumber) {
        this.userID = user;
        this.itemNumber = itemNumber;
    }

    public Item() {
    }

    public String getPhotoAddress() {
        return "img/items/item" + itemNumber + ".jpg";
    }

    public int getPriceForOneItem() {
        return switch (this.itemNumber) {
            case 1 -> 15 ;
            case 2 -> 200 ;
            case 3 -> 25 ;
            case 4 -> 45 ;
            case 5 -> 95 ;
            case 6 -> 20 ;
            case 7 -> 30 ;
            case 8 -> 450 ;
            default -> 0;
        };
    }

    public String getItemName() {
        return switch (this.itemNumber) {
            case 1 -> "Silver ring";
            case 2 -> "Gold earrings with cubic zirconia";
            case 3 -> "Silver earrings with cubic zirconia";
            case 4 -> "Silver ring with tanzanite";
            case 5 -> "Rose gold cubic zirconia earrings";
            case 6 -> "Silver ring with cubic zirconia";
            case 7 -> "Earrings with cubic zirconia";
            case 8 -> "Gold earrings with diamonds";
            default -> "";
        };
    }
}
