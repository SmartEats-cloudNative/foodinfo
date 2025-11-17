package com.example.foodinfo.controller;



import com.example.foodinfo.dto.FoodCataloguePage;
import com.example.foodinfo.dto.FoodDto;
import com.example.foodinfo.service.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class FoodcataloguecontrollerTest {

    @Mock
    private FoodService foodCatalogueService;

    @InjectMocks
    private FoodController foodCatalogueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFoodItem_ShouldReturnCreatedStatus() {
        // Arrange
        FoodDto foodItemDTO = new FoodDto();
        when(foodCatalogueService.addFoodItems(any(FoodDto.class))).thenReturn(foodItemDTO);

        // Act
        ResponseEntity<FoodDto> response = foodCatalogueController.addFoodItems(foodItemDTO);

        // Assert
        verify(foodCatalogueService, times(1)).addFoodItems(foodItemDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == foodItemDTO;
    }

    @Test
    void fetchRestauDetailsWithFoodMenu_ShouldReturnOkStatus() {
        // Arrange
        int restaurantId = 123;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        when(foodCatalogueService.fetchRestautantDetailsAndFoodCatalogueById(restaurantId)).thenReturn(foodCataloguePage);

        // Act
        ResponseEntity<FoodCataloguePage> response = foodCatalogueController.fetchRestautantDetailsAndFoodCatalogueById(restaurantId);

        // Assert
        verify(foodCatalogueService, times(1)).fetchRestautantDetailsAndFoodCatalogueById(restaurantId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == foodCataloguePage;
    }
}