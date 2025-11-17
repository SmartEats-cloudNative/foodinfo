package com.example.foodinfo.service;
import com.example.foodinfo.dto.FoodCataloguePage;
import com.example.foodinfo.dto.FoodDto;
import com.example.foodinfo.dto.Restaurant;
import com.example.foodinfo.entity.Food;
import com.example.foodinfo.mapper.FoodMapper;
import com.example.foodinfo.repo.FoodRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FoodServiceTest {
    @Mock
    private FoodRepo foodItemRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FoodService foodCatalogueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFoodItem_ShouldSaveFoodItemAndReturnMappedDTO() {
        // Arrange
        FoodDto foodItemDTO = new FoodDto();
        Food foodItem = new Food();
        when(foodItemRepo.save(any(Food.class))).thenReturn(foodItem);

        // Act
        FoodDto result = foodCatalogueService.addFoodItems(foodItemDTO);

        // Assert
        verify(foodItemRepo, times(1)).save(any(Food.class));
        Assertions.assertEquals(FoodMapper.Instance.foodToFoodDto(foodItem), result);
    }

    @Test
    void fetchFoodCataloguePageDetails_ShouldReturnFoodCataloguePage() {
        // Arrange
        int restaurantId = 123;
        List<Food> foodItemList = Arrays.asList(new Food());
        Restaurant restaurant = new Restaurant();
        when(foodItemRepo.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(Restaurant.class))).thenReturn(restaurant);

        // Act
        FoodCataloguePage result = foodCatalogueService.fetchRestautantDetailsAndFoodCatalogueById(restaurantId);

        // Assert
        verify(foodItemRepo, times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Restaurant.class));
        Assertions.assertEquals(foodItemList, result.getFoodItemList());
        Assertions.assertEquals(restaurant, result.getRestaurant());
    }
}
