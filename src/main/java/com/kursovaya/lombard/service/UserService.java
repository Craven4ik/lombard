package com.kursovaya.lombard.service;

import com.kursovaya.lombard.entitys.User;
import com.kursovaya.lombard.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User user) {
        User user1 = userRepo.findUserByUsername(user.getUsername());
        if (user1 != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean resaveUser(User user) {
        userRepo.save(user);
        return true;
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public User getUserAuth() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
