package com.rudra.response;

import com.rudra.model.USER_ROLE;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class AuthResponse {

    private  String  jwtToken ;

    private  String message;

    private USER_ROLE role;

}
