package com.rudra.service;

import com.rudra.model.USER_ROLE;
import com.rudra.model.User;
import com.rudra.repositatory.UserRepositatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class customService implements UserDetailsService {
    @Autowired
    UserRepositatory userRepositatory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=userRepositatory.findByEmail(username);
         if (user==null){
             throw  new UsernameNotFoundException("User not found ");
         }
         USER_ROLE role=user.getRole();
        List<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

         return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
