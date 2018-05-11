package com.misstouch.springbootshirojwt.controller;

import com.misstouch.springbootshirojwt.common.ReslutBean;
import com.misstouch.springbootshirojwt.dto.LoginDto;
import com.misstouch.springbootshirojwt.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hechao
 * @date create in 16:20 2018/5/11/011
 */
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/user")
    @RequiresPermissions("user:query")
    public ReslutBean getuser(@RequestBody LoginDto loginDto){
        return iUserService.getuser(loginDto);
    }

}
