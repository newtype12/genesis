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
        StringBuilder sb = new StringBuilder("role-"+key);
        set.add(sb.toString(),value);
    }

    public void remove(Integer key, Integer value) {
        SetOperations<String, Integer> set = redisTemplate.opsForSet();
        StringBuilder sb = new StringBuilder("role-"+key);
        set.remove(sb.toString(),value);
    }

    public Integer pop(Integer key){
        StringBuilder sb = new StringBuilder("role-"+key);
        return (Integer) redisTemplate.opsForSet().randomMember(sb.toString());
    }

    public void check(Integer key){

        StringBuilder sb = new StringBuilder("role-"+key);
        System.out.println(redisTemplate.opsForSet().members(sb.toString()));
    }

    public Integer getAvailableUserID(){

        if(redisTemplate.opsForSet().size("role-1")>0){
            return (Integer)redisTemplate.opsForSet().pop("role-1");
        }else if(redisTemplate.opsForSet().size("role-2")>0){
            return (Integer)redisTemplate.opsForSet().pop("role-2");
        }else if(redisTemplate.opsForSet().size("role-3")>0){
            return (Integer)redisTemplate.opsForSet().pop("role-3");
        }
        return 0;
    }

}
