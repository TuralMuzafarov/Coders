package com.FinalProject.Coders.DTOs;


import lombok.Data;
import lombok.Setter;

@Data
public class LoginResponse implements DTO {
    private String token;

    @Setter
    private long expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExp(long expiresIn) {
        this.expiresIn = expiresIn;
    }


}
