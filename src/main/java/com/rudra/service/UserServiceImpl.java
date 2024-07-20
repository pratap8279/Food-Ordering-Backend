package com.rudra.service;

import com.rudra.config.jwtProvider;
import com.rudra.model.User;
import com.rudra.repositatory.UserRepositatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements  UserService{
    @Autowired
    private UserRepositatory userRepositatory;
    @Autowired
    private com.rudra.config.jwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);
        return  user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepositatory.findByEmail(email);
        if (user==null){
            throw  new Exception("user not found");
        }
        return  user;
    }
}
