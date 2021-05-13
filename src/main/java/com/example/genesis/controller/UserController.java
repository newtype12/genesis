package com.example.genesis.controller;

import com.example.genesis.data.entity.User;
import com.example.genesis.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public User login(@RequestParam Integer id) {
        return service.login(id);
    }

    @PostMapping("/logout")
    public Integer logout(@RequestParam Integer id) {
        return service.logout(id);
    }

    @PostMapping("/complete")
    public Boolean complete(@RequestParam Integer id) {
        return service.complete(id);
    }

    @PostMapping("/trans")
    public Boolean trans(@RequestParam Integer id) {
        return service.transOrder(id);
    }

}
