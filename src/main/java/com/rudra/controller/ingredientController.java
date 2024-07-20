package com.rudra.controller;

import com.rudra.Request.ingredientCategoryRequest;
import com.rudra.Request.ingredientIteamRequest;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.model.ingredientCategory;
import com.rudra.model.ingredientsIteam;
import com.rudra.service.RestaurantServiceImpl;
import com.rudra.service.UserService;
import com.rudra.service.UserServiceImpl;
import com.rudra.service.ingredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
public class ingredientController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
  private   RestaurantServiceImpl restaurantService;

    @Autowired
    private ingredientServiceImpl ingredientService;

    @PostMapping("/createIngredientCategory")
    public ResponseEntity<ingredientCategory> createIngredientCategory(@RequestBody ingredientCategoryRequest req) throws Exception {

        ingredientCategory ingredientCategory=ingredientService.createIndgredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }



    @GetMapping("/findIngredientCategoryById/{id}")
    public ResponseEntity<ingredientCategory> findIngredientCategoryById(@PathVariable Integer id) throws Exception {

        ingredientCategory ingredientCategory=ingredientService.findIngredientCategoryById(id);

        return new ResponseEntity<>(ingredientCategory, HttpStatus.OK);
    }

    @GetMapping("/findIngredientByRestaurantId/{id}")
    public ResponseEntity<List<ingredientCategory>> findIngredientByRestaurantId(@PathVariable Integer id,
                                                                                  @RequestHeader("Authorization") String jwt) throws Exception {

        List<ingredientCategory> ingredientCategory=ingredientService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(ingredientCategory, HttpStatus.OK);
    }

    @PostMapping("/createIngredientsIteam")
    public ResponseEntity<ingredientsIteam> createIngredientIteam(@RequestBody ingredientIteamRequest req) throws Exception {

        ingredientsIteam ingredientsIteam=ingredientService.createIngredients(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(ingredientsIteam,HttpStatus.CREATED);

    }

    @PutMapping("/updateStock/{id}")
    public ResponseEntity<ingredientsIteam> updateStock(@PathVariable Integer id) throws Exception {
         ingredientsIteam ingredientsIteam=ingredientService.updateStock(id);
         return new ResponseEntity<>(ingredientsIteam,HttpStatus.OK);
    }

    @GetMapping("/getAllTheIngredient/{id}")
    public ResponseEntity<List<ingredientsIteam>> findAllIngredientRestaurant(@PathVariable Integer id) throws Exception {
        List<ingredientsIteam> ingredientsIteamList=ingredientService.findRestaurantByIngredient(id);
        return new ResponseEntity<>(ingredientsIteamList,HttpStatus.OK);
    }
    @GetMapping("/getAllTheIngredientCategory/{id}")
    public ResponseEntity<List<ingredientCategory>> findAllIngredientCategoryRestaurant(@PathVariable Integer id,
                                                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        List<ingredientCategory> ingredientsIteamList=ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientsIteamList,HttpStatus.OK);
    }
    @GetMapping("/indAllIngredientItemByCategoryId/{id}")
    public ResponseEntity<List<ingredientsIteam>> findAllIngredientItemByCategoryId(@PathVariable Integer id) throws Exception {
        List<ingredientsIteam> ingredientsIteamList=ingredientService.findIngredientItemByCategory(id);
        return new ResponseEntity<>(ingredientsIteamList,HttpStatus.OK);
    }
}
