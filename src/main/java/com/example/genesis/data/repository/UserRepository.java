package com.example.genesis.data.repository;

import com.example.genesis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("update User u set u.status=1 where u.id=:id")
    Integer login(Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.status=0 where u.id=:id")
    Integer logout(Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.orderId=:orderId where u.id=:id")
    Integer updateOrder(Integer id, Integer orderId);

    @Transactional
    @Modifying
    @Query("update User u set u.orderId= null where u.id=:id")
    Integer clearOrder(Integer id);


    @Query("SELECT u FROM User u WHERE u.status = 1 and u.orderId is null")
    List<User> findOnlineUser();

}
