package com.springbootcachetutorial.service;

import com.springbootcachetutorial.domain.entity.User;
import com.springbootcachetutorial.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        log.info("saving user" );
        return userRepository.save(user);
    }

    @CachePut( value="user", key="#id")
    public User updateUser(Integer id, User user) {
        User userUpd = userRepository.findById(id)
                .orElseThrow();
        userUpd.setAge(user.getAge());
        userUpd.setName(user.getName());

        log.info("Updating user: ", id);
        return userRepository.save(userUpd);
    }

    @CacheEvict( value="user")
    public void deleteUser(Integer id) {
        log.info("Delete user id: ", id);
        User user = userRepository.findById(id)
                .orElseThrow();
        userRepository.delete(user);
    }


    @Cacheable( value="user", key="#id")
    public User findById(Integer id) {
        log.info("Getting user from database with id: " + id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            return null;
        } else {
            return optionalUser.get();
        }
    }
}
