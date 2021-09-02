package com.quikdev.controller;

import com.quikdev.dto.DadosLogin;
import com.quikdev.dto.UserAutheticatedDTO;
import com.quikdev.model.User;
import com.quikdev.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthenticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }
    public AuthenticationController(){ }


    @PostMapping("/login")
    public ResponseEntity<UserAutheticatedDTO> autenticar(@RequestBody DadosLogin dadosLogin, @RequestHeader String authorization){
        User user = userAuthenticationService.authenticate(dadosLogin, authorization);
        return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer ", authorization), HttpStatus.ACCEPTED);
    }
}
