package com.sanbro.redis.service;

import com.sanbro.redis.Exceptions.UserAlreadyExistException;
import com.sanbro.redis.Exceptions.UserDoesNotExistException;
import com.sanbro.redis.dto.UserDto;
import com.sanbro.redis.entity.User;
import com.sanbro.redis.mapper.UserMapper;
import com.sanbro.redis.repository.UserRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<String> registerUser(UserDto user){
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistException("User already exists with the mail "+user.getEmail());

        User details = userMapper.UserDtoToUser(user);
        userRepository.save(details);
        return new ResponseEntity<>("User registration success", HttpStatus.CREATED);
    }

    @Cacheable(value = "user",key = "#email")
    public UserDto getUserById(String email){
        System.out.println("GETTING VALUES FROM THE DATABASE");
        if(userRepository.findByEmail(email).isPresent())
            return userMapper.userToUserDto(userRepository.findByEmail(email).get());
        throw new UserDoesNotExistException("No user found with the specified email "+email);
    }

    @CacheEvict(value="user",key="#userDto.email")
    public UserDto editUserInfoAndReturnUpdatedInfo(UserDto userDto){
       User details = userRepository.findByEmail(userDto.getEmail()).orElseThrow(()->new UserDoesNotExistException("User does not exist"));
       details.setName(userDto.getName());
       userRepository.save(details);
       return userMapper.userToUserDto(details);
    }
    @CacheEvict(value = "user",key="#email")
    public String deleteUserInfo(String email){
        User details = userRepository.findByEmail(email).orElseThrow(()->new UserDoesNotExistException("user does not exist"));
        userRepository.delete(details);
        return "Deleted successfully";
    }
}
