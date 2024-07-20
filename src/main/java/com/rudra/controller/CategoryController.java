package com.rudra.controller;

import com.rudra.model.Category;
import com.rudra.model.User;
import com.rudra.service.CategoryServiceImpl;
import com.rudra.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody Category category
                                                   ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Category categorydb=categoryService.createCategory(category.getName(),user.getId());
        return new ResponseEntity<>(categorydb, HttpStatus.OK);
    }

    @GetMapping("/findCategoryByRestaurantId/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Integer id

    ) throws Exception {
             User user=userService.findUserByJwtToken(jwt);
            List<Category> category=categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @GetMapping("/findCategoryByUserId/{id}")
    public ResponseEntity<List<Category>> findCategoryByUserId(@RequestHeader("Authorization") String jwt

    ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Category> category=categoryService.findCategoryByUserId(user.getId());
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
