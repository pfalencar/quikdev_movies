package com.quikdev.dto;

import com.quikdev.model.User;

public class UserAutheticatedDTO {
    private String tipo;
    private String email;
    private String nome;
    private String token;
    private String senha;

    public UserAutheticatedDTO(String email, String nome, String token, String tipo, String senha) {
        this.email = email;
        this.nome = nome;
        this.token = token;
        this.tipo = tipo;
        this.senha = senha;
    }

    public UserAutheticatedDTO(){}

    public UserAutheticatedDTO(String tipo, String email, String nome, String token) {
        this.tipo = tipo;
        this.email = email;
        this.nome = nome;
        this.token = token;
    }

    public static UserAutheticatedDTO toDTO(User user, String tipo, String token) {
        return new UserAutheticatedDTO(user.getEmail(), user.getNome(), token, tipo);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSenha() {
        return senha;
    }
}
