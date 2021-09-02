package com.dio.jwt.service;

import com.dio.jwt.dto.DadosLogin;
import com.dio.jwt.exception.ExistingEmailException;
import com.dio.jwt.exception.ExpiredTokenException;
import com.dio.jwt.exception.InvalidLoginException;
import com.dio.jwt.exception.InvalidTokenException;
import com.dio.jwt.model.User;
import com.dio.jwt.repository.UserRepository;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
