package com.misstouch.springbootshirojwt.controller;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;
import com.misstouch.springbootshirojwt.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hechao
 * @date create in 12:43 2018/5/11/011
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    /**
     * 登录接口
     * @param loginDto  用户信息Dto
     * @return
     */
    @PostMapping("/login")
    public ReslutBean login(@RequestBody LoginDto loginDto){
        return iLoginService.login(loginDto);
    }

}
