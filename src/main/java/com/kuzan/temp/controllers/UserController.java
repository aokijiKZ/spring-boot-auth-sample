package com.kuzan.temp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kuzan.temp.dtos.UserDto;
import com.kuzan.temp.models.UserModel;
import com.kuzan.temp.repositories.UserRepository;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userRepository.getUsers().stream()
            .map(user -> new UserDto(user.getName(), user.getEmail()))
            .toList();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userRepository.getUsers().stream()
            .filter(user -> user.getId().equals(id))
            .map(user -> new UserDto(user.getName(), user.getEmail()))
            .findFirst()
            .orElse(null);
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDto userDto){
        userRepository.addUser(new UserModel(userDto.getName(), userDto.getEmail()));
        return "User added successfully";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UserDto userDto){
        UserModel updateUser = new UserModel(id, userDto.getName(), userDto.getEmail());
        userRepository.editUser(updateUser);
        return "User Updated successfully";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id){
        UserModel userToDelete = userRepository.getUsers().stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElse(null);
        
        if (userToDelete != null) {
            userRepository.removeUser(userToDelete);
            return "User deleted successfully";
        }

        return "User not found";
    }
}
