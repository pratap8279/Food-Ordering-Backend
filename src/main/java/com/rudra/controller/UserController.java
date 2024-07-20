package com.rudra.controller;

import com.rudra.model.User;
import com.rudra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtTokenProfile(@RequestHeader("Authorization") String jwt) throws Exception {
          try {
              User user = userService.findUserByJwtToken(jwt);
              return new ResponseEntity<>(user, HttpStatus.OK);
          }catch(Exception e){
              throw  new Exception("bad input");
          }
//        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);S
    }

}
