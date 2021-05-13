package com.example.genesis.service;

import com.example.genesis.data.entity.Order;
import com.example.genesis.data.entity.User;
import com.example.genesis.data.repository.UserRepository;
import com.example.genesis.util.RedisSetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final OrderService orderService;

    private final RedisSetUtil redisSetUtil;

    /**
     * 確認user列表
     *
     * @param
     * @return
     */
    public List<User> getUsers() {
        redisSetUtil.check();

        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    /**
     * 模擬登入
     *
     * @param id
     * @return
     */
    public User login(Integer id) {
        Integer result = userRepository.login(id);
        if (result > 0) {
            User user = getUser(id);
            redisSetUtil.add(user.getRole(), id);
            log.info("user {} is login", id);
            return user;
        }
        return null;
    }

    /**
     * 模擬登出
     *
     * @param id
     * @return
     */
    public Integer logout(Integer id) {
        User user = getUser(id);
        redisSetUtil.remove(user.getRole(), id);
        return userRepository.logout(id);
    }

    /**
     * 完成工單
     *
     * @param id
     * @return
     */
    public Boolean complete(Integer id) {
        User user = getUser(id);

        if (orderService.updateOrderStatus(user.getOrderId(), 2, user.getId()) > 0) {
            userRepository.clearOrder(id);
            redisSetUtil.add(user.getRole(), id);
            log.info("user {}, complete order Id:{}", id, user.getOrderId());
            return true;
        }
        return false;
    }

    /**
     * 分單
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Integer distributeOrder(Integer userId, Integer orderId) {
        return userRepository.updateOrder(userId, orderId);
    }

    /**
     * 取得可用user
     *
     * @return
     */
    public Integer getAvailableUserID() {

        if (redisSetUtil.getSetSize("role-1") > 0) {
            return redisSetUtil.popSet("role-1");
        } else if (redisSetUtil.getSetSize("role-2") > 0) {
            return redisSetUtil.popSet("role-2");
        } else if (redisSetUtil.getSetSize("role-3") > 0) {
            return redisSetUtil.popSet("role-3");
        }
        return 0;
    }

    /**
     * 轉單給tl or pm
     *
     * @param id
     * @return
     */
    public Boolean transOrder(Integer id) {

        Integer toId = getUpperUserID();

        if (toId > 0) {
            User user = getUser(id);
            Order order = orderService.findOrder(user.getOrderId());
            //取得可轉單user
            //

            if (orderService.updateOrderStatus(user.getOrderId(), 1, toId) > 0) {

                distributeOrder(toId, user.getOrderId());
                userRepository.clearOrder(id);
                redisSetUtil.add(user.getRole(), id);
                log.info("user {}, trans orderId {} to {}", id, user.getOrderId(), toId);
                return true;
            }
        }

        return false;
    }

    /**
     * 取得可轉單user
     *
     * @return
     */
    public Integer getUpperUserID() {

        if (redisSetUtil.getSetSize("role-2") > 0) {
            return redisSetUtil.popSet("role-2");
        } else if (redisSetUtil.getSetSize("role-3") > 0) {
            return redisSetUtil.popSet("role-3");
        }
        return 0;
    }

    @PostConstruct
    public void setLoginUser() {
        List<User> users = userRepository.findOnlineUser();
        if (users.size() > 0) {
            System.out.println(users.size());
            for (User user : users) {
                redisSetUtil.add(user.getRole(), user.getId());
            }
        }
    }
}
