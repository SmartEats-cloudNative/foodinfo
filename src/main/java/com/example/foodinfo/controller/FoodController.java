package com.example.foodinfo.controller;

import com.example.foodinfo.dto.FoodCataloguePage;
import com.example.foodinfo.dto.FoodDto;
import com.example.foodinfo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping("/getAllFoodItems")
    public ResponseEntity<List<FoodDto>> getFoodItems(){
        List<FoodDto> allfoods=foodService.fetchallfooditems();
        return new ResponseEntity<>(allfoods, HttpStatus.OK);
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodDto> addFoodItems(@RequestBody FoodDto foodDto){
        FoodDto result = foodService.addFoodItems(foodDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/fetchRestaurantAndFoodDetailsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestautantDetailsAndFoodCatalogueById(@PathVariable Integer restaurantId){
        FoodCataloguePage foodCataloguePage =foodService.fetchRestautantDetailsAndFoodCatalogueById(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
