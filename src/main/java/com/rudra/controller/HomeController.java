package com.rudra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping ("/home")
    public ResponseEntity<String> home(){
        return  new ResponseEntity<>("Welcome to food delivery Project", HttpStatus.OK);
    }

}
