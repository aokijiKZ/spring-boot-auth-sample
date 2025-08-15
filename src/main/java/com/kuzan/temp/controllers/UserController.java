package com.kuzan.temp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kuzan.temp.dtos.UserDto;
import com.kuzan.temp.models.UserModel;
import com.kuzan.temp.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserDto(user.getName(), user.getEmail()))
            .toList();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userRepository.findAll().stream()
            .filter(user -> user.getId().equals(id))
            .map(user -> new UserDto(user.getName(), user.getEmail()))
            .findFirst()
            .orElse(null);
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDto userDto){
        userRepository.save(new UserModel(userDto.getName(), userDto.getEmail()));
        return "User added successfully";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserModel updateUser = new UserModel(id, userDto.getName(), userDto.getEmail());
        userRepository.save(updateUser);
        return "User Updated successfully";
    }

    @PostMapping("/users/search")
    public UserDto getUserByEmail(@Valid @RequestBody UserDto userDto){
        // return userRepository.findByEmail(userDto.getEmail())
        //     .map(user -> new UserDto(user.getName(), user.getEmail()))
        //     .orElse(null);
        UserDto foundUser = userRepository.findByEmail(userDto.getEmail())
            .map(user -> new UserDto(user.getName(), user.getEmail()))
            .orElse(null);

        if(foundUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return foundUser;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id){
        UserModel userToDelete = userRepository.findAll().stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElse(null);
        
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            return "User deleted successfully";
        }

        return "User not found";
    }
}
