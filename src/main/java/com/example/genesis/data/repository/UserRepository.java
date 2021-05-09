package com.example.genesis.data.repository;

import com.example.genesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("update User u set u.status=1 where u.id=:id")
    Integer login(Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.status=0 where u.id=:id")
    Integer logout(Integer id);
}
