package com.misstouch.springbootshirojwt.service;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;

/**
 * @author hechao
 * @date create in 16:25 2018/5/11/011
 */
public interface IUserService {

    ReslutBean getuser(LoginDto loginDto);
}
