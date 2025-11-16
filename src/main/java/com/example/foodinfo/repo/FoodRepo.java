package com.example.foodinfo.repo;

import com.example.foodinfo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food,Integer> {

    List<Food> findByRestaurantId(Integer restaurantId);
}
