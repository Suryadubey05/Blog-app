package com.example.Blog_App.users;

import com.example.Blog_App.users.dto.CreateUserRequest;
import com.example.Blog_App.users.dto.LoginUserRequest;
import com.example.Blog_App.users.dto.UserResponse;
import lombok.NonNull;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createUser(CreateUserRequest request) {
        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(newUser);
    }


    public UserEntity loginUser(@NonNull String username, @NonNull String password) {
        Optional<UserEntity> optionalUser= userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        UserEntity savedUser = optionalUser.get();

        var matchPass = passwordEncoder.matches(password, savedUser.getPassword());

        if(!matchPass){
            throw new RuntimeException("Password is not matched");
        }

        return savedUser;
    }


    public UserEntity getUser(Long userId) {
        Optional<UserEntity> optionalUser= userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        UserEntity savedUser = optionalUser.get();

        return savedUser;
    }
}
