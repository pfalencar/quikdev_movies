package com.quikdev.controller;

import com.quikdev.dto.UserAutheticatedDTO;
import com.quikdev.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    public UserRegistrationController(){

    }

    @PostMapping("/user")
    public ResponseEntity<String> registrate(@RequestBody UserAutheticatedDTO userBody){
        String token = userRegistrationService.registrate(userBody);
        return  new ResponseEntity<String>(token, HttpStatus.CREATED);
    }

}
