package com.misstouch.springbootshirojwt.service;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;
import com.misstouch.springbootshirojwt.entity.User;

/**
 * @author hechao
 * @date create in 12:34 2018/5/11/011
 */
public interface ILoginService {

    /**
     * 进行登录认证
     * @param loginDto  登录dto，其中包含用户信息
     * @return ReslutBean  封装的返回类
     */
    ReslutBean login(LoginDto loginDto);

}
