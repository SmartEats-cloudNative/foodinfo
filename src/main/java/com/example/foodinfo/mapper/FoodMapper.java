package com.example.foodinfo.mapper;

import com.example.foodinfo.dto.FoodDto;
import com.example.foodinfo.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodMapper {
    FoodMapper Instance = Mappers.getMapper(FoodMapper.class);
    Food foodDtoToFood(FoodDto foodDto);
    FoodDto foodToFoodDto(Food food);

}
