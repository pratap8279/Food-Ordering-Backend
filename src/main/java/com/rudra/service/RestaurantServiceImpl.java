package com.rudra.service;

import com.rudra.Request.RestaurantRequest;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.repositatory.AddressRepositatory;
import com.rudra.repositatory.RestaurantRepository;
import com.rudra.repositatory.UserRepositatory;
import com.rudra.restaurantDto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepositatory addressRepositatory;
    @Autowired
    private UserRepositatory userRepositatory;
    @Override
    public Restaurant createRestaurant( RestaurantRequest request, User user) {
        Restaurant restaurant= new Restaurant();
        restaurant.setAddress(request.getAddress());
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
//        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

         Restaurant restaurant1= restaurantRepository.save(restaurant);
        return restaurant1;
    }

    @Override
    public Restaurant updateRestaurant(Integer restaurantId, RestaurantRequest updated) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updated.getCuisineType());
        }if(restaurant.getDescription()!=null){
            restaurant.setDescription(updated.getDescription());
        }if(restaurant.getName()!=null){
            restaurant.setName(updated.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) throws Exception {

         Restaurant restaurant=findRestaurantById(restaurantId);
         restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurantList=restaurantRepository.findAll();
        return  restaurantList;
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return  restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Integer id) throws Exception {
        Optional<Restaurant> opt=restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw  new Exception("Restaurant not found by id");
        }
        return opt.get();

    }

    @Override
    public Restaurant getRestaurantByUserId(Integer id) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(id);
        if(restaurant==null){
            throw  new Exception("Restaurant found with ownerid ");

        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Integer restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDto dto= new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);
       boolean isFavorited=false;
       List<RestaurantDto> favorites=user.getFavorites();
       for(RestaurantDto favorite:favorites){
           if(favorite.getId().equals(restaurantId)){
               isFavorited=true;
               break;
           }
        }
       if(isFavorited){
           favorites.removeIf(favorite->favorite.getId().equals(restaurantId));
       }
           favorites.add(dto);

        userRepositatory.save(user);
           return dto;
    }

    @Override
    public Restaurant updatedRestaurantStatus(Integer id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
     return    restaurantRepository.save(restaurant);
    }
}
