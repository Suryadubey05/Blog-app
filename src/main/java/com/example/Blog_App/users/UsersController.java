package com.example.Blog_App.users;

import com.example.Blog_App.security.JWTService;
import com.example.Blog_App.users.dto.CreateUserRequest;
import com.example.Blog_App.users.dto.UserResponse;
import com.example.Blog_App.users.dto.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JWTService jwtService;

    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){
        UserEntity savedUser = userService.createUser(request);
        URI savedUserUri = URI.create("/users/" + savedUser.getId());

        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(
                jwtService.createJwt(savedUser.getId())
        );

        return ResponseEntity.created(savedUserUri)
                .body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity savedUser = userService.loginUser(request.getUsername(), request.getPassword());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(
                jwtService.createJwt(savedUser.getId())
        );
        return ResponseEntity.ok(userResponse);
    }

}
