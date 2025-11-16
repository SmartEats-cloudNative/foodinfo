package com.example.foodinfo.service;

import com.example.foodinfo.dto.FoodCataloguePage;
import com.example.foodinfo.dto.FoodDto;
import com.example.foodinfo.dto.Restaurant;
import com.example.foodinfo.entity.Food;
import com.example.foodinfo.mapper.FoodMapper;
import com.example.foodinfo.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
     FoodRepo foodRepo;
    @Autowired
    RestTemplate restTemplate;



    public FoodDto addFoodItems(FoodDto foodDto) {
       Food foodItemsSaved = foodRepo.save(FoodMapper.Instance.foodDtoToFood(foodDto));
       return FoodMapper.Instance.foodToFoodDto(foodItemsSaved);
    }

    public FoodCataloguePage fetchRestautantDetailsAndFoodCatalogueById(Integer restaurantId) {
        List<Food> foodItemsList= foodCatalogueList(restaurantId);
        Restaurant restaurant = restaurantDetailsByIdFromRestaurantMS(restaurantId);
   return createFoodCataloguePage(foodItemsList,restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<Food> foodItemsList , Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setRestaurant(restaurant);
        foodCataloguePage.setFoodItemList(foodItemsList);
    return foodCataloguePage;
    }

    private List<Food> foodCatalogueList(Integer restaurantId) {
        return foodRepo.findByRestaurantId(restaurantId);
    }
    private Restaurant restaurantDetailsByIdFromRestaurantMS(Integer restaurantId) {
         return restTemplate.getForObject("http://listofrestaurants/restaurant/fetchById/" + restaurantId, Restaurant.class);

    }

    public List<FoodDto> fetchallfooditems() {
           List<Food> allfoods=foodRepo.findAll();

        return allfoods.stream().map(food -> FoodMapper.Instance.foodToFoodDto(food)).collect(Collectors.toList());
    }
}
