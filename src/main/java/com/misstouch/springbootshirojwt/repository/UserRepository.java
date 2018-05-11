package com.misstouch.springbootshirojwt.repository;

import com.misstouch.springbootshirojwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hechao
 * @date create in 12:12 2018/5/11/011
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 使用用户名查询用户信息
     * @param username 用户名
     * @return User    用户信息
     */
    User findByUsername(String username);

}
