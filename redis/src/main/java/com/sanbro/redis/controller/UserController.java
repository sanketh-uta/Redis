package com.sanbro.redis.controller;

import com.sanbro.redis.dto.UserDto;
import com.sanbro.redis.entity.User;
import com.sanbro.redis.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto user){
        return userService.registerUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserById(@RequestParam Map<String,String> map){
        return ResponseEntity.ok(userService.getUserById(map.get("email")));
    }

    @PatchMapping("/user")
    public ResponseEntity<UserDto> editUserInfoAndReturnUpdatedInfo(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.editUserInfoAndReturnUpdatedInfo(userDto));
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam Map<String,String> map){
        return ResponseEntity.ok(userService.deleteUserInfo(map.get("email")));
    }
}
