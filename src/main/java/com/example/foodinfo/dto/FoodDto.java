package com.example.foodinfo.dto;


import com.example.foodinfo.entity.Food;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private int id;
    private String itemName;
    private String itemDescription;
    private boolean isVegetarian;
    private BigDecimal price;
    private Integer restaurantId;
    private Integer quantity;

}
