package com.quikdev.service;

import com.quikdev.dto.DadosLogin;
import com.quikdev.exception.ExistingEmailException;
import com.quikdev.exception.InvalidLoginException;
import com.quikdev.model.User;
import com.quikdev.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    public User authenticate(DadosLogin dados, String token){
        User user = userRepository.findByEmail(dados.getEmail()).orElseThrow(ExistingEmailException::new);
        if(dados.getSenha().equals(user.getSenha()) && !token.isEmpty() && tokenService.validate(token)) {
            return user;
        }
        else {
            throw new InvalidLoginException();
        }
    }



}
