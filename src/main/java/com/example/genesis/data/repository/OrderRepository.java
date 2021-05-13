package com.example.genesis.data.repository;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query(value ="INSERT INTO `order`(content, customer_name, title) values (:content, :name , :title);", nativeQuery = true)
    Integer insertOrder(@Param("content") String content, @Param("name") String name, @Param("title") String title);

    @Query(value = "select o from Order o where o.status=:status")
    List<Order> getUndoneOrder(@Param("status") Integer status);

    @Transactional
    @Modifying
    @Query("update Order o set o.status=:status, o.lastUpdateUser=:userId where o.id=:orderId")
    Integer updateOrder(Integer orderId, Integer status, Integer userId);
}
