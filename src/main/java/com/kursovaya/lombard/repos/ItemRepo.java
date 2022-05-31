package com.kursovaya.lombard.repos;

import com.kursovaya.lombard.entitys.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
