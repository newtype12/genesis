package com.example.genesis.data.repository;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query(value ="INSERT INTO `order`(content, customer_name, title) values (:content, :name , :title);", nativeQuery = true)
    public int insertOrder(@Param("content") String content, @Param("name") String name, @Param("title") String title);
}
