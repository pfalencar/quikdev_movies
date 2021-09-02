package com.quikdev.service;

import com.quikdev.dto.UserAutheticatedDTO;
import com.quikdev.model.User;
import com.quikdev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserRegistrationService {

    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String registrate(UserAutheticatedDTO userAutheticatedDTO){
        Optional<User> userOptional = userRepository.findByEmail(userAutheticatedDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E-mail already used");
        }
        User user = createUser(userAutheticatedDTO);
        String token = tokenService.generateToken(user);
        User userSaved = userRepository.save(user);
        return token;
    }

    public User createUser(UserAutheticatedDTO userAutheticatedDTO) {
        User user = new User(userAutheticatedDTO.getNome(), userAutheticatedDTO.getEmail(), userAutheticatedDTO.getSenha());
        return user;
    }

}
