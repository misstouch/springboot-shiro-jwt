package com.misstouch.springbootshirojwt.service.impl;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;
import com.misstouch.springbootshirojwt.entity.User;
import com.misstouch.springbootshirojwt.repository.UserRepository;
import com.misstouch.springbootshirojwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hechao
 * @date create in 16:25 2018/5/11/011
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ReslutBean getuser(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null) {
            return new ReslutBean(200,"success",user);
        }
        return new ReslutBean(200,"error","user dose not exist");
    }
}
