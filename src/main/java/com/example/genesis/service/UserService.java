package com.example.genesis.service;

import com.example.genesis.data.entity.User;
import com.example.genesis.data.repository.UserRepository;
import com.example.genesis.util.RedisSetUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RedisSetUtil redisSetUtil;

    public List<User> getUsers() {
        redisSetUtil.check(1);
        redisSetUtil.check(2);
        redisSetUtil.check(3);
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public User login(Integer id){
      Integer result= userRepository.login(id);
      if(result>0){
          User user=  getUser(id);
          redisSetUtil.add(user.getRole(), id);
          return user;
      }
        return null;
    }

    public Integer logout(Integer id){
        User user=  getUser(id);
        redisSetUtil.remove(user.getRole(), id);
        return userRepository.logout(id);
    }

    public User getAvailableUser(){
        return new User();
    }

    public User complete(Integer id){
        User user=  getUser(id);
        redisSetUtil.add(user.getRole(), id);
        return user;
    }

}
