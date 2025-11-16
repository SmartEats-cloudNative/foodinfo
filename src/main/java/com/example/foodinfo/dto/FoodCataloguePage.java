package com.example.foodinfo.dto;

import com.example.foodinfo.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {
    private List<Food> foodItemList;
    private Restaurant restaurant;
}
