package com.example.genesis.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSetUtil {

    private final RedisTemplate redisTemplate;

    public void add(Integer key, Integer value) {
        StringBuilder sb = new StringBuilder("role-" + key);
        redisTemplate.opsForSet().add(sb.toString(), value);
    }

    public void remove(Integer key, Integer value) {
        StringBuilder sb = new StringBuilder("role-" + key);
        redisTemplate.opsForSet().remove(sb.toString(), value);
    }

    public Integer pop(Integer key) {
        StringBuilder sb = new StringBuilder("role-" + key);
        return (Integer) redisTemplate.opsForSet().randomMember(sb.toString());
    }

    public Integer getSetSize(String setName) {
        return redisTemplate.opsForSet().size(setName).intValue();
    }

    public Integer popSet(String setName) {
        return (Integer) redisTemplate.opsForSet().pop(setName);
    }

    public void check() {
        System.out.println(redisTemplate.opsForSet().size("role-1"));
        System.out.println(redisTemplate.opsForSet().size("role-2"));
        System.out.println(redisTemplate.opsForSet().size("role-3"));
    }
}
