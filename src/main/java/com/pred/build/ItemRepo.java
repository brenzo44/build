package com.pred.build;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item, Integer>{
    @Query(value="SELECT * FROM pred_items WHERE name= ?1", nativeQuery=true)
    Item findOneItem(String first);
}
