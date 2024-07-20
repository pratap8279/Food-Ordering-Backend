package com.rudra.controller;

import com.rudra.Request.LoginRequest;
import com.rudra.config.jwtProvider;
import com.rudra.model.Cart;
import com.rudra.model.USER_ROLE;
import com.rudra.model.User;
import com.rudra.repositatory.CartRepositatory;
import com.rudra.repositatory.UserRepositatory;
import com.rudra.response.AuthResponse;
import com.rudra.response.MesageResponse;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepositatory userRepositatory;
    @Autowired
     private PasswordEncoder passwordEncoder;
    @Autowired
     private jwtProvider jwtProvider;
    @Autowired
     private com.rudra.service.customService customService;
    @Autowired
     private CartRepositatory cartRepositatory;
   @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist= userRepositatory.findByEmail(user.getEmail());
       MesageResponse mesageResponse= new MesageResponse();
       mesageResponse.setMessage("Email all ready Register");
        if(isEmailExist!=null){
            throw new Exception("Email is already Registered");
        }
        User createUsr=new User();
        createUsr.setEmail(user.getEmail());
        createUsr.setFullName(user.getFullName());
        createUsr.setRole(user.getRole());
        createUsr.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser= userRepositatory.save(createUsr);

        Cart cart= new Cart();
        cart.setCustomer(savedUser);
        cartRepositatory.save(cart);


        Authentication authentication= new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwtToken(jwt);
        authResponse.setMessage("User register successfully");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest req){

        String userName=req.getEmail();
        String Password=req.getPassword();
        Authentication authentication=authentication(userName,Password);
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
         String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        String jwt=jwtProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwtToken(jwt);
        authResponse.setMessage("Login successfully");
        authResponse.setRole(USER_ROLE.valueOf(role));

         return  new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    private Authentication authentication(String userName, String Password) {
        UserDetails userDetails=customService.loadUserByUsername(userName);
        if (userDetails==null){
            throw  new BadCredentialsException("Invalid userName");

        }
        if(!passwordEncoder.matches(Password ,userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid password....");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
