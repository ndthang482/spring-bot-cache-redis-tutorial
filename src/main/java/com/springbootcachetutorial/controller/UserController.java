package com.springbootcachetutorial.controller;

import com.springbootcachetutorial.service.UserService;
import com.springbootcachetutorial.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userService.saveUser(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        User updateUser = userService.updateUser(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}