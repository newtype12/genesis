package com.example.genesis.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisSetUtil {

    private final RedisTemplate redisTemplate;

    public void add(Integer key, Integer value) {
        SetOperations<String, Integer> set = redisTemplate.opsForSet();
        StringBuilder sb = new StringBuilder("role-" + key);
        set.add(sb.toString(), value);
    }

    public void remove(Integer key, Integer value) {
        SetOperations<String, Integer> set = redisTemplate.opsForSet();
        StringBuilder sb = new StringBuilder("role-" + key);
        set.remove(sb.toString(), value);
    }

    public Integer pop(Integer key) {
        StringBuilder sb = new StringBuilder("role-" + key);
        return (Integer) redisTemplate.opsForSet().randomMember(sb.toString());
    }

    public Integer getSetSize(String setName) {
        return redisTemplate.opsForSet().size(setName).intValue();    }

    public Integer popSet(String setName) {
        return (Integer) redisTemplate.opsForSet().pop(setName);
    }

}
