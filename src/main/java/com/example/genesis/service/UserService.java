package com.example.genesis.service;

import com.example.genesis.data.entity.User;
import com.example.genesis.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(
            cacheNames = "user"
            , key = "#id"
            , unless = "#result==null"
    )
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

}
