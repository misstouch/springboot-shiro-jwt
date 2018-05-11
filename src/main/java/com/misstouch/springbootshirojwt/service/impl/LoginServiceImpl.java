package com.misstouch.springbootshirojwt.service.impl;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;
import com.misstouch.springbootshirojwt.entity.User;
import com.misstouch.springbootshirojwt.repository.UserRepository;
import com.misstouch.springbootshirojwt.service.ILoginService;
import com.misstouch.springbootshirojwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author hechao
 * @date create in 12:46 2018/5/11/011
 */
@Service("iLoginService")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ReslutBean login(LoginDto loginDto) {

        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            return new ReslutBean(200,"success", JwtUtils.sign(user.getUsername()));
        }
        return null;
    }
}
