package com.kursovaya.lombard.entitys;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User implements UserDetails {
    private String username;
    private String password;
    @Transient
    private String checkPassword;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToMany(mappedBy = "userID", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Item> list = new HashSet<Item>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getItemCount(int itemNumber){
        for (Item item : list){
            if (item.getItemNumber() == itemNumber){
                return item.getItemCount();
            }
        }
        return 0;
    }

    public Item getItem(int itemNumber){
        for (Item item: list){
            if (item.getItemNumber() == itemNumber)
                return item;
        }
        return null;
    }

    public String getOrderPrice(){
        int total = 0;
        for (Item item: this.list){
            total += item.getPriceForOneItem()*item.getItemCount();
        }
        return total + " $";
    }
}
