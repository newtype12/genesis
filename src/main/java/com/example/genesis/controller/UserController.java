package com.example.genesis.controller;

import com.example.genesis.data.entity.User;
import com.example.genesis.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/search")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping()
    public User getUser(@RequestParam Integer id) {
        return service.getUser(id);
    }

}
